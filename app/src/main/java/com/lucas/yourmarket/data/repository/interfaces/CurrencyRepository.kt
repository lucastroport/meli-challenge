package com.lucas.yourmarket.data.repository.interfaces

interface CurrencyRepository {
    suspend fun fetchCurrencies()
}
