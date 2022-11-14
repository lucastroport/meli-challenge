package com.lucas.yourmarket.data.storage.datastore.interfaces

import androidx.paging.PagingSource
import com.lucas.yourmarket.data.model.Product
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery

interface ProductLocalDatastore {
    suspend fun storeProduct(product: Product)
    suspend fun storeProducts(products: List<Product>)
    suspend fun clearAllProducts()
    suspend fun updateProduct(product: Product)
    suspend fun getProductById(id: String): ProductWithCurrencyQuery?
    suspend fun getProducts(): PagingSource<Int, ProductWithCurrencyQuery>?
}