package com.lucas.yourmarket.presentation.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.lucas.yourmarket.data.callback.RemoteMediatorCallback
import com.lucas.yourmarket.data.paging.ProductRemoteMediator
import com.lucas.yourmarket.data.storage.datastore.interfaces.ProductLocalDatastore
import com.lucas.yourmarket.domain.usecases.ClearStorageUseCase
import com.lucas.yourmarket.presentation.models.ProductUI
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel
import com.lucas.yourmarket.presentation.screens.detail.ProductDetailRoute
import kotlinx.coroutines.flow.*
import org.koin.core.component.inject

class HomeViewModel(
    private val routeNavigator: RouteNavigator
) : BaseViewModel(),
    RouteNavigator by routeNavigator,
    RemoteMediatorCallback
{

    companion object {
        private val CLASS_TAG = HomeViewModel::class.qualifiedName

        private const val PAGE_SIZE = 10
        private const val SIZE_MULTIPLIER = 3
    }

    // DI
    private val clearStorageUseCase: ClearStorageUseCase by inject()
    private val productLocalDatastore: ProductLocalDatastore by inject()

    // UI
    val products: MutableStateFlow<PagingData<ProductUI>> = MutableStateFlow(PagingData.from(emptyList()))
    val searchField = MutableLiveData("")
    val spinner = MutableLiveData(false)

    // Callback
    private var callback: RemoteMediatorCallback? = null

    // UI Callbacks
    fun onItemClicked(id: String) = navigateToRoute(ProductDetailRoute.get(id))

    @OptIn(ExperimentalPagingApi::class)
    fun onSearchEnter() {
        searchField.value?.trim()?.let { input ->
            if (input.isNotEmpty()) {
                launchIO {
                    withLoadingSpinner(spinner = spinner) {
                        callback = this@HomeViewModel
                        clearStorageUseCase.execute()
                        Pager(
                            config = PagingConfig(
                                enablePlaceholders = false,
                                pageSize = PAGE_SIZE,
                                maxSize = PAGE_SIZE * SIZE_MULTIPLIER
                            ),
                            remoteMediator = ProductRemoteMediator(
                                query = input,
                                mediatorCallback = callback
                            ),
                            pagingSourceFactory = { productLocalDatastore.getProducts() }
                        )
                            .flow
                            .cachedIn(viewModelScope)
                            .collectLatest {
                                spinner.postValue(false)
                                products.emit(it.map { productWithQuery ->
                                    ProductUI(
                                        id = productWithQuery.id,
                                        name = productWithQuery.title,
                                        price = productWithQuery.price.toString(),
                                        currencySymbol = productWithQuery.currency,
                                        imageThumbnailUrl = productWithQuery.thumbnail,
                                        isFreeShipping = productWithQuery.freeShipping
                                    )
                                })
                            }
                    }
                }
            }
        }
    }

    override suspend fun onRefreshReceived() {
        clearStorageUseCase.execute()
    }

    override fun onEmptyResponse() {
        Log.i(CLASS_TAG, "No results were found")
    }

    override fun onErrorReceived() {
        Log.e(CLASS_TAG, "Error ocurred")
    }
}
