package com.lucas.yourmarket.presentation.screens.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.hadilq.liveevent.LiveEvent
import com.lucas.yourmarket.R
import com.lucas.yourmarket.data.callback.RemoteMediatorCallback
import com.lucas.yourmarket.data.paging.ProductRemoteMediator
import com.lucas.yourmarket.data.storage.datastore.interfaces.ProductLocalDatastore
import com.lucas.yourmarket.domain.usecases.ClearStorageUseCase
import com.lucas.yourmarket.presentation.models.DialogUI
import com.lucas.yourmarket.presentation.models.ProductUI
import com.lucas.yourmarket.presentation.navigation.RouteNavigator
import com.lucas.yourmarket.presentation.screens.BaseViewModel
import com.lucas.yourmarket.presentation.screens.detail.ProductDetailRoute
import com.lucas.yourmarket.presentation.util.toCurrency
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import org.koin.core.component.inject

class HomeViewModel(
    private val routeNavigator: RouteNavigator
) : BaseViewModel(),
    RemoteMediatorCallback,
    RouteNavigator by routeNavigator,
    BaseViewModel.UiCallback {

    companion object {
        private const val PAGE_SIZE = 10
        private const val SIZE_MULTIPLIER = 3
    }

    // DI
    private val clearStorageUseCase: ClearStorageUseCase by inject()
    private val productLocalDatastore: ProductLocalDatastore by inject()

    // UI
    val products: MutableStateFlow<PagingData<ProductUI>> =
        MutableStateFlow(PagingData.from(emptyList()))
    val searchField = MutableLiveData("")
    val isFirstLoaded = MutableLiveData(true)

    // Events
    val noResultsEvent = LiveEvent<Boolean>()

    // Callback
    private var callback: RemoteMediatorCallback? = null

    // UI Callbacks
    fun onItemClicked(id: String) {
        navigateToRoute(ProductDetailRoute.get(id))
    }

    init {
        uiCallback = this@HomeViewModel
    }

    fun onSearchEnter() {
        launchIO {
            searchField.value?.trim()?.let { input ->
                if (input.isNotEmpty()) {
                    searchProduct(input)
                }
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    private suspend fun searchProduct(input: String) {
        isFirstLoaded.postValue(false)
        noResultsEvent.postValue(false)

        withLoadingSpinner {
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
                    fullScreenLoading.postValue(false)
                    products.emit(it.map { productWithQuery ->
                        ProductUI(
                            id = productWithQuery.id,
                            name = productWithQuery.title,
                            price = productWithQuery.price?.toCurrency(),
                            currencySymbol = productWithQuery.currency,
                            imageThumbnailUrl = productWithQuery.thumbnail,
                            isFreeShipping = productWithQuery.freeShipping
                        )
                    })
                }
        }
    }

    override suspend fun onRefreshReceived() {
        clearStorageUseCase.execute()
    }

    override fun onEmptyResponse() {
        noResultsEvent.postValue(true)
    }

    override fun onErrorReceived(message: String?) {
        val context: Context by inject()
        showRetryErrorDialog(
            dialog =
                DialogUI(
                    title = context.getString(R.string.generic_error_title),
                    message = message
                ),
            routeNavigator = routeNavigator,
            onRetry = {
                launchIO {
                    navigateUp()
                    delay(500)
                    onSearchEnter()
                }
            }
        )
    }

    override fun handleError(dialogInfo: DialogUI) {
        showRetryErrorDialog(
            dialog = dialogInfo,
            routeNavigator = routeNavigator,
            onRetry = {
                launchIO {
                    navigateUp()
                    delay(500)
                    onSearchEnter()
                }
            }
        )
    }
}
