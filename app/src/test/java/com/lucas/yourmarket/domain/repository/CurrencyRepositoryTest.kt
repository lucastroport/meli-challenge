package com.lucas.yourmarket.domain.repository

import com.lucas.yourmarket.data.remote.datastore.implementations.CurrencyRemoteDatastoreImpl
import com.lucas.yourmarket.data.remote.datastore.interfaces.CurrencyRemoteDatastore
import com.lucas.yourmarket.data.storage.datastore.implementations.CurrencyLocalDatastoreImpl
import com.lucas.yourmarket.data.storage.datastore.interfaces.CurrencyLocalDatastore
import com.lucas.yourmarket.domain.model.Currency
import com.lucas.yourmarket.domain.repository.implementations.CurrencyRepositoryImpl
import com.lucas.yourmarket.domain.repository.interfaces.CurrencyRepository
import com.lucas.yourmarket.test.BaseTest
import com.lucas.yourmarket.test.KoinTestRule
import com.lucas.yourmarket.test.TestModuleFactory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import org.mockito.Mockito.mock

class CurrencyRepositoryTest : BaseTest() {

    @get:Rule
    val koinRule = KoinTestRule(TestModuleFactory.produce())

    private val localStore = mock(CurrencyLocalDatastore::class.java)
    private val remoteStore = mock(CurrencyRemoteDatastore::class.java)
    private val repository: CurrencyRepository = CurrencyRepositoryImpl(
        currencyLocalDatastore = localStore,
        currencyRemoteDatastore = remoteStore
    )

    companion object {
        private val mockCurrencies = listOf(
            Currency(
                id = "1",
                symbol = "$",
                description = "currency desc",
                decimalPlaces = 1
            )
        )
    }

    @Before
    fun setup() {
        stopKoin()
        runBlocking {
            Mockito.`when`(
                remoteStore.getCurrencies()
            ).thenReturn(
                mockCurrencies
            )
        }
    }

    @Test
    fun `validate get currencies`() = runBlocking {
        repository.fetchCurrencies()
        Mockito.verify(remoteStore).getCurrencies()
        Mockito.verify(localStore).storeCurrencies(mockCurrencies)
    }
}
