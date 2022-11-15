package com.lucas.yourmarket.data.remote.datastore.implementations

import android.util.Log
import com.lucas.yourmarket.domain.model.Currency
import com.lucas.yourmarket.data.remote.datastore.interfaces.CurrencyRemoteDatastore
import com.lucas.yourmarket.data.remote.services.CurrencyService

class CurrencyRemoteDatastoreImpl(
    private val currencyService: CurrencyService
): CurrencyRemoteDatastore{

    companion object {
        private val CLASS_TAG = CurrencyRemoteDatastoreImpl::class.qualifiedName
    }

    override suspend fun getCurrencies(): List<Currency> {
        return try {
            currencyService.fetchCurrencies()
        }catch (e: Exception){
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
            emptyList()
        }
    }

}