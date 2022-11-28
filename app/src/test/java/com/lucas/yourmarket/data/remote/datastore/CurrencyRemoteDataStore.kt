package com.lucas.yourmarket.data.remote.datastore

import com.lucas.yourmarket.data.remote.datastore.implementations.CurrencyRemoteDatastoreImpl
import com.lucas.yourmarket.data.remote.datastore.interfaces.CurrencyRemoteDatastore
import com.lucas.yourmarket.data.remote.services.CurrencyService
import com.lucas.yourmarket.domain.model.Currency
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import kotlin.test.assertEquals


class CurrencyRemoteDataStore {

    private val service = Mockito.mock(CurrencyService::class.java)
    private val datastore: CurrencyRemoteDatastore = CurrencyRemoteDatastoreImpl(service)

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
    fun `should fetch currency with network`() {
        runBlocking {
            Mockito.`when`(
                service.fetchCurrencies()
            ).thenReturn(
                currenciesMock
            )
            val actual = datastore.getCurrencies()
            Mockito.verify(service).fetchCurrencies()
            assertEquals(currenciesMock, actual)
        }
    }

    @Test
    fun `should fetch currency without network`() {
        runBlocking {
            Mockito.`when`(
                service.fetchCurrencies()
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.getCurrencies()
            Mockito.verify(service).fetchCurrencies()
            assertEquals(emptyList(), actual)
        }
    }
}
