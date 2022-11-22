package com.lucas.yourmarket.presentation.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.lucas.yourmarket.domain.model.SellerReputation
import com.lucas.yourmarket.domain.usecases.ProductUseCase
import com.lucas.yourmarket.domain.usecases.StateUseCase
import com.lucas.yourmarket.domain.usecases.UserUseCase
import com.lucas.yourmarket.presentation.models.UserReputationLevels
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel
import org.koin.core.component.inject

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator
) : BaseViewModel(),
    RouteNavigator by routeNavigator {

    // DI
    private val productUseCase: ProductUseCase by inject()
    private val userUseCase: UserUseCase by inject()
    private val stateUseCase: StateUseCase by inject()

    private val productId = ProductDetailRoute.getIndexFrom(savedStateHandle)

    // UI
    val name: LiveData<String?> = MutableLiveData("")
    val price: LiveData<String?> = MutableLiveData("")
    val hasWarranty: LiveData<Boolean?> = MutableLiveData(false)
    val warranty: LiveData<String?> = MutableLiveData("")
    val imagesUrls: LiveData<List<String>?> = MutableLiveData(listOf())
    val isFreeShipping: LiveData<Boolean?> = MutableLiveData(false)
    val sellerName: LiveData<String?> = MutableLiveData("")
    val currencySymbol: LiveData<String?> = MutableLiveData("")
    val sellerLocation: LiveData<String?> = MutableLiveData("")
    val isPlatinumUser: LiveData<Boolean?> = MutableLiveData(false)
    val reputation: LiveData<String?> = MutableLiveData("")
    val productCondition: LiveData<String?> = MutableLiveData("")
    val soldQuantity: LiveData<Int?> = MutableLiveData(0)

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        launchIO {
            withLoadingSpinner {
                productUseCase.execute(ProductUseCase.Request(itemId = productId)).handlingErrors {
                    it.data?.product?.let { fetchedProduct ->
                        launchIO {
                            userUseCase.execute(
                                fetchedProduct.sellerId?.let { it1 -> UserUseCase.Request(it1) }
                            ).handlingErrors { fetchedUser ->
                                fetchedUser.data?.user?.let { user ->
                                    launchIO {
                                        user.address?.state?.let { stateId ->
                                            stateUseCase.execute(StateUseCase.Request(stateId))
                                                .handlingErrors { stateResponse ->
                                                    fullScreenLoading.postValue(false)
                                                    val state = stateResponse.data?.state
                                                    name.postValue(fetchedProduct.title)
                                                    price.postValue(fetchedProduct.price.toString())
                                                    hasWarranty.postValue(!fetchedProduct.warranty.isNullOrBlank())
                                                    warranty.postValue(fetchedProduct.warranty)
                                                    val urls =
                                                        fetchedProduct.pictures?.mapNotNull { picture -> picture.url }
                                                    imagesUrls.postValue(urls)
                                                    isFreeShipping.postValue(fetchedProduct.freeShipping)
                                                    currencySymbol.postValue(fetchedProduct.currency)
                                                    productCondition.postValue(fetchedProduct.condition)
                                                    soldQuantity.postValue(fetchedProduct.soldQuantity)
                                                    sellerName.postValue(user.nickname)
                                                    sellerLocation.postValue("${user.address?.city} ${state?.name}")
                                                    isPlatinumUser.postValue(user.sellerReputation?.powerSellerStatus == SellerReputation.STATUS_TYPE_PLATINIUM)
                                                    val userReputationLevel =
                                                        user.sellerReputation?.levelId ?: ""
                                                    val userLevel: UserReputationLevels = when {
                                                        userReputationLevel.startsWith(1.toString()) -> UserReputationLevels.USER_REPUTATION_1
                                                        userReputationLevel.startsWith(2.toString()) -> UserReputationLevels.USER_REPUTATION_2
                                                        userReputationLevel.startsWith(3.toString()) -> UserReputationLevels.USER_REPUTATION_3
                                                        userReputationLevel.startsWith(4.toString()) -> UserReputationLevels.USER_REPUTATION_4
                                                        userReputationLevel.startsWith(5.toString()) -> UserReputationLevels.USER_REPUTATION_5
                                                        else -> UserReputationLevels.USER_REPUTATION_0
                                                    }
                                                    reputation.postValue(userLevel.produce())
                                                }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


