package com.lucas.yourmarket.data.repository.implementations

import com.lucas.yourmarket.data.remote.datastore.interfaces.ProductRemoteDatastore
import com.lucas.yourmarket.data.repository.interfaces.ProductRepository
import com.lucas.yourmarket.data.storage.datastore.interfaces.ProductLocalDatastore
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery

class ProductRepositoryImpl(
    private val productLocalDatastore: ProductLocalDatastore,
    private val productRemoteDatastore: ProductRemoteDatastore
): ProductRepository {
    override suspend fun fetchProduct(id: String): ProductWithCurrencyQuery? {
        val response = productRemoteDatastore.fetchProductById(id)
        response?.let {
            productLocalDatastore.storeProduct(it)
        }
        return productLocalDatastore.getProductById(id)
    }
}