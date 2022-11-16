package com.lucas.yourmarket.data.remote.datastore.interfaces

import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.domain.model.response.ProductsPagingResponse

interface ProductRemoteDatastore {
    suspend fun fetchProducts(query: String, offSet: Int, limit: Int): ProductsPagingResponse?
    suspend fun fetchProductById(itemId: String): Product?
}
