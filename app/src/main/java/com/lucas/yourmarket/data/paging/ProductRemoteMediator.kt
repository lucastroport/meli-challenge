package com.lucas.yourmarket.data.paging

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.lucas.yourmarket.data.callback.RemoteMediatorCallback
import com.lucas.yourmarket.data.remote.datastore.interfaces.ProductRemoteDatastore
import com.lucas.yourmarket.data.storage.datastore.interfaces.ProductLocalDatastore
import com.lucas.yourmarket.data.storage.datastore.interfaces.RemoteKeyDatastore
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery
import com.lucas.yourmarket.domain.model.key.YourMarketRemoteKey
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.lucas.yourmarket.R
import javax.net.ssl.SSLHandshakeException

@ExperimentalPagingApi
class ProductRemoteMediator(
    private val query: String,
    private val mediatorCallback: RemoteMediatorCallback?,
) : RemoteMediator<Int, ProductWithCurrencyQuery>(), KoinComponent{

    companion object {
        private const val START_PAGE_INDEX = 0
        private const val ERROR_FETCHING_PRODUCTS = "There's been an error fetching the products"
    }
    // DI
    private val productLocalDatastore: ProductLocalDatastore by inject()
    private val productRemoteDatastore: ProductRemoteDatastore by inject()
    private val remoteKeyDatastore: RemoteKeyDatastore by inject()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductWithCurrencyQuery>
    ): MediatorResult {

        val currentPage: Int = when(loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                remoteKey?.nextPage?.minus(1) ?: START_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                remoteKey?.prevPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = false
                    )
            }
            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                remoteKey?.nextPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = false
                    )
            }
        }

        try {
            val pageSize = state.config.pageSize
            val offSet = currentPage * pageSize
            productRemoteDatastore.fetchProducts(
                query = query,
                offSet = offSet,
                limit = pageSize,
            )?.let { response ->

                val result = response.results.filter { it.containsMandatoryFields() }
                val endOfPaginationReached = result.isEmpty()

                if(endOfPaginationReached && isFirstPage(loadType = loadType, currentPage = currentPage, state = state)) {
                    mediatorCallback?.onEmptyResponse()
                }
                if (loadType == LoadType.REFRESH) {
                    mediatorCallback?.onRefreshReceived()
                }

                val prevKey = if (currentPage == START_PAGE_INDEX) null else currentPage - 1
                val nextKey = if (endOfPaginationReached) null else currentPage + 1
                val remoteKeys = result.map { product ->
                    YourMarketRemoteKey(
                        id = product.id,
                        prevPage = prevKey,
                        nextPage = nextKey
                    )
                }
                remoteKeyDatastore.storeRemoteKeys(remoteKeys)
                productLocalDatastore.storeProducts(result)

                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

            } ?: throw Exception(ERROR_FETCHING_PRODUCTS)

        } catch (e: Exception) {
            if (isFirstPage(loadType = loadType, currentPage = currentPage, state = state)) {
                e.message?.let { mediatorCallback?.onErrorReceived(it) }
            }
            return MediatorResult.Error(e)
        }
    }

    private fun isFirstPage(
        loadType: LoadType,
        currentPage: Int,
        state: PagingState<Int, ProductWithCurrencyQuery>
    ): Boolean {
        return state.pages.isEmpty()
                && loadType == LoadType.REFRESH
                && currentPage == START_PAGE_INDEX
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ProductWithCurrencyQuery>
    ): YourMarketRemoteKey? {
        return state.firstItemOrNull()?.let { product ->
            remoteKeyDatastore.getRemoteKeyById(product.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ProductWithCurrencyQuery>
    ): YourMarketRemoteKey? {
        return state.lastItemOrNull()?.let { product ->
            remoteKeyDatastore.getRemoteKeyById(product.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ProductWithCurrencyQuery>
    ): YourMarketRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { product ->
                remoteKeyDatastore.getRemoteKeyById(product.id)
            }
        }
    }
}