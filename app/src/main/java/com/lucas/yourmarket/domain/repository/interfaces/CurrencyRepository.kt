package com.lucas.yourmarket.domain.repository.interfaces

interface CurrencyRepository {
    suspend fun fetchCurrencies()
}
