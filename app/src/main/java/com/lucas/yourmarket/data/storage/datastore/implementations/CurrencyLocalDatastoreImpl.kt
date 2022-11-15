package com.lucas.yourmarket.data.storage.datastore.implementations

import android.util.Log
import com.lucas.yourmarket.domain.model.Currency
import com.lucas.yourmarket.data.storage.dao.CurrencyDao
import com.lucas.yourmarket.data.storage.datastore.interfaces.CurrencyLocalDatastore

class CurrencyLocalDatastoreImpl(
    private val currencyDao: CurrencyDao
): CurrencyLocalDatastore {

    companion object {
        private val CLASS_TAG = CurrencyLocalDatastoreImpl::class.qualifiedName
    }

    override suspend fun getCurrencyById(id: String): Currency? {
        return try {
            currencyDao.getById(id)
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
            null
        }
    }

    override suspend fun storeCurrencies(currencies: List<Currency>) {
        try {
            currencyDao.insertAll(currencies)
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
        }
    }

    override suspend fun clearAllCurrencies() {
        try {
            currencyDao.clearAll()
        } catch (e: Exception) {
            Log.e(CLASS_TAG,Log.getStackTraceString(e))
        }
    }
}
