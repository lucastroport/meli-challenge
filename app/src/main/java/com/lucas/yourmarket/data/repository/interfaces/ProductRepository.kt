package com.lucas.yourmarket.data.repository.interfaces

import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery

interface ProductRepository {

    suspend fun fetchProduct(id: String): ProductWithCurrencyQuery?
}