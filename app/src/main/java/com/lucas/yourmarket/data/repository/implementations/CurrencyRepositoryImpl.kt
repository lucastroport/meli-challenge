package com.lucas.yourmarket.data.repository.implementations

import com.lucas.yourmarket.data.remote.datastore.interfaces.CurrencyRemoteDatastore
import com.lucas.yourmarket.data.repository.interfaces.CurrencyRepository
import com.lucas.yourmarket.data.storage.datastore.interfaces.CurrencyLocalDatastore

class CurrencyRepositoryImpl(
    private val currencyLocalDatastore: CurrencyLocalDatastore,
    private val currencyRemoteDatastore: CurrencyRemoteDatastore
) : CurrencyRepository {

    override suspend fun fetchCurrencies() {
        val response = currencyRemoteDatastore.getCurrencies()
        currencyLocalDatastore.storeCurrencies(response)
    }
}
