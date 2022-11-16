package com.lucas.yourmarket.domain.repository.implementations

import com.lucas.yourmarket.data.remote.datastore.interfaces.CurrencyRemoteDatastore
import com.lucas.yourmarket.domain.repository.interfaces.CurrencyRepository
import com.lucas.yourmarket.data.storage.datastore.interfaces.CurrencyLocalDatastore
import com.lucas.yourmarket.domain.model.Currency

class CurrencyRepositoryImpl(
    private val currencyLocalDatastore: CurrencyLocalDatastore,
    private val currencyRemoteDatastore: CurrencyRemoteDatastore
) : CurrencyRepository {

    override suspend fun fetchCurrencies(): List<Currency> {
        val response = currencyRemoteDatastore.getCurrencies()
        currencyLocalDatastore.storeCurrencies(response)
        return response
    }
}
