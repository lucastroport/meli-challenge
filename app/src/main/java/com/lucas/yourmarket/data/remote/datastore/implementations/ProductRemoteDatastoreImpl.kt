package com.lucas.yourmarket.data.remote.datastore.implementations

import android.util.Log
import com.lucas.yourmarket.BuildConfig
import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.domain.model.response.ProductsPagingResponse
import com.lucas.yourmarket.data.remote.datastore.interfaces.ProductRemoteDatastore
import com.lucas.yourmarket.data.remote.services.ProductService

class ProductRemoteDatastoreImpl(
    private val productService: ProductService
): ProductRemoteDatastore {

    companion object {
        private val CLASS_TAG = ProductRemoteDatastoreImpl::class.qualifiedName
    }

    override suspend fun fetchProducts(query: String, offset: Int, limit: Int): ProductsPagingResponse? {
        return try {
            productService.fetchProductsPage(
                siteId = BuildConfig.MELI_SITE_ID,
                offset = offset,
                limit = limit,
                query = query
            )
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
            null
        }
    }

    override suspend fun fetchProductById(itemId: String): Product? {
        return try {
            productService.fetchProduct(itemId)
        }catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
            null
        }
    }
}