package com.lucas.yourmarket.presentation.screens.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.lucas.yourmarket.R
import com.lucas.yourmarket.domain.usecases.ProductUseCase
import com.lucas.yourmarket.domain.usecases.StateUseCase
import com.lucas.yourmarket.domain.usecases.UserUseCase
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel
import org.koin.core.component.inject
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery
import com.lucas.yourmarket.domain.model.SellerReputation
import com.lucas.yourmarket.domain.model.State
import com.lucas.yourmarket.domain.model.User
import com.lucas.yourmarket.domain.model.response.Status
import com.lucas.yourmarket.presentation.models.DialogUI
import com.lucas.yourmarket.presentation.models.UserReputationLevels
import com.lucas.yourmarket.presentation.utils.toCurrency
import kotlinx.coroutines.delay

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator
) : BaseViewModel(),
    RouteNavigator by routeNavigator,
    BaseViewModel.UiCallback {
    // DI
    private val productUseCase: ProductUseCase by inject()
    private val userUseCase: UserUseCase by inject()
    private val stateUseCase: StateUseCase by inject()

    private val productId = ProductDetailRoute.getIndexFrom(savedStateHandle)

    // UI
    val name = MutableLiveData("")
    val price = MutableLiveData("")
    val hasWarranty = MutableLiveData(false)
    val warranty = MutableLiveData("")
    val imagesUrls: LiveData<List<String>?> = MutableLiveData(listOf())
    val isFreeShipping = MutableLiveData(false)
    val sellerName = MutableLiveData("")
    val currencySymbol = MutableLiveData("")
    val sellerLocation = MutableLiveData("")
    val isPlatinumUser= MutableLiveData(false)
    val reputation = MutableLiveData("")
    val productCondition = MutableLiveData("")
    val soldQuantity = MutableLiveData(0)

    init {
        launchIO {
            fetchProduct()
        }
        uiCallback = this@ProductDetailViewModel
    }

    private suspend fun fetchProduct() {
        var errors = true
        withLoadingSpinner {
            val productResult = productUseCase.execute(ProductUseCase.Request(itemId = productId))
            if (productResult is Status.Success) {
                val userResult =
                    userUseCase.execute(productResult.data?.product?.sellerId?.let { userId ->
                        UserUseCase.Request(userId)
                    })
                if (userResult is Status.Success) {
                    stateUseCase.execute(userResult.data?.user?.address?.state?.let {
                        StateUseCase.Request(it)
                    }).handlingErrors { stateResult ->
                        fullScreenLoading.postValue(false)
                        errors = false
                        buildUiResult(
                            productResult.data?.product,
                            userResult.data?.user,
                            stateResult.data?.state
                        )
                    }
                }
            }
        }
        if (errors) {
            val context: Context by inject()
            handleError(
                DialogUI(
                    message = context.getString(R.string.error_fetching_product),
                    title = context.getString(R.string.generic_error_title)
                )
            )
        }
    }

    private fun buildUiResult(
        product: ProductWithCurrencyQuery?,
        user: User?,
        state: State?
    ) {
        product?.let {
            user.let {
                state.let {
                    name.postValue(product.title)
                    price.postValue(product.price?.toCurrency())
                    hasWarranty.postValue(!product.warranty.isNullOrBlank())
                    warranty.postValue(product.warranty)
                    val urls = product.pictures?.mapNotNull { picture -> picture.url }
                    imagesUrls.postValue(urls)
                    isFreeShipping.postValue(product.freeShipping)
                    currencySymbol.postValue(product.currency)
                    productCondition.postValue(product.condition)
                    soldQuantity.postValue(product.soldQuantity)
                    sellerName.postValue(user?.nickname)
                    sellerLocation.postValue("${user?.address?.city} ${state?.name}")
                    isPlatinumUser.postValue(user?.sellerReputation?.powerSellerStatus == SellerReputation.STATUS_TYPE_PLATINIUM)
                    val userReputationLevel =
                        user?.sellerReputation?.levelId ?: ""
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

    override fun handleError(dialogInfo: DialogUI?) {
        showRetryErrorDialog(
            dialog = dialogInfo,
            routeNavigator = routeNavigator,
            onRetry = {
                launchIO {
                    navigateUp()
                    delay(500)
                    fetchProduct()
                }
            }
        )
    }
}
