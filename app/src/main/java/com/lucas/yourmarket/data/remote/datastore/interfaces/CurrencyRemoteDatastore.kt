package com.lucas.yourmarket.data.remote.datastore.interfaces

import com.lucas.yourmarket.domain.model.Currency

interface CurrencyRemoteDatastore {
    suspend fun getCurrencies(): List<Currency>
}