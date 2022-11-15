package com.lucas.yourmarket.data.storage.datastore.interfaces

import com.lucas.yourmarket.domain.model.Currency

interface CurrencyLocalDatastore {
    suspend fun getCurrencyById(id: String): Currency?
    suspend fun storeCurrencies(curencies: List<Currency>)
    suspend fun clearAllCurrencies()
}
