package com.lucas.yourmarket.data.remote.datastore.interfaces

import com.lucas.yourmarket.data.model.Product
import com.lucas.yourmarket.data.model.response.ProductsPagingResponse

interface ProductRemoteDatastore {
    suspend fun fetchProducts(query: String, offset: Int, limit: Int): ProductsPagingResponse?
    suspend fun fetchProductById(itemId: String): Product?
}
