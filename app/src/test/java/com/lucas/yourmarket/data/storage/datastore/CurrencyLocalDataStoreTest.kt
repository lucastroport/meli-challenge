package com.lucas.yourmarket.data.storage.datastore

import com.lucas.yourmarket.data.storage.dao.CurrencyDao
import com.lucas.yourmarket.data.storage.datastore.implementations.CurrencyLocalDatastoreImpl
import com.lucas.yourmarket.data.storage.datastore.interfaces.CurrencyLocalDatastore
import com.lucas.yourmarket.domain.model.Currency
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito

class CurrencyLocalDataStoreTest {

    private val dao = Mockito.mock(CurrencyDao::class.java)
    private val datastore: CurrencyLocalDatastore = CurrencyLocalDatastoreImpl(dao)

    companion object {
        private val currenciesMock = listOf(
            Currency(
                id = "1",
                symbol = "$",
                description = "some description",
                decimalPlaces = 3
            ),
            Currency(
                id = "2",
                symbol = "$",
                description = "some description",
                decimalPlaces = 3
            )
        )
    }

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `should store currencies`() {
        runBlocking {
            datastore.storeCurrencies(currenciesMock)
            Mockito.verify(dao).insertAll(currenciesMock)
        }
    }

}