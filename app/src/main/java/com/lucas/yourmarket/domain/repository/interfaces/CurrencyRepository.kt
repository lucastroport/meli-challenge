package com.lucas.yourmarket.domain.repository.interfaces

import com.lucas.yourmarket.domain.model.Currency

interface CurrencyRepository {
    suspend fun fetchCurrencies(): List<Currency>
}
