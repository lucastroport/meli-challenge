package com.lucas.yourmarket.domain.repository

import com.lucas.yourmarket.data.remote.datastore.interfaces.ProductRemoteDatastore
import com.lucas.yourmarket.data.storage.datastore.interfaces.ProductLocalDatastore
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery
import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.domain.model.Shipping
import com.lucas.yourmarket.domain.repository.implementations.ProductRepositoryImpl
import com.lucas.yourmarket.domain.repository.interfaces.ProductRepository
import com.lucas.yourmarket.test.BaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import kotlin.test.assertEquals

class ProductRepositoryTest : BaseTest() {

    private val localStore = Mockito.mock(ProductLocalDatastore::class.java)
    private val remoteStore = Mockito.mock(ProductRemoteDatastore::class.java)
    private val repository: ProductRepository = ProductRepositoryImpl(localStore, remoteStore)

    companion object {
        private val mockProduct = Product(
            id = "1",
            title = "some title",
            price = 1.0,
            condition = "new" ,
            _thumbnail = "some thumbnail",
            _shipping = Shipping(freeShipping = true),
            _pictures = emptyList(),
            sellerId = 1,
            warranty = "12 months",
            currencyId = "some id",
            soldQuantity = 1
        )
        private val mockProductWithCurrencyQuery = ProductWithCurrencyQuery(
            id = "1",
            title = "testTitle",
            price = 1.00,
            condition = "new",
            thumbnail = "test thumbnail",
            warranty = "test warranty",
            currency = "$",
            pictures = emptyList(),
            freeShipping = false,
            sellerId = 1,
            soldQuantity = 1
        )
    }

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `validate fetch product with local store only`() {
        runBlocking {
            Mockito.`when`(
                remoteStore.fetchProductById(Mockito.anyString())
            ).thenReturn(
                null
            )
            Mockito.`when`(
                localStore.getProductById(Mockito.anyString())
            ).thenReturn(
                mockProductWithCurrencyQuery
            )
            val actual = repository.fetchProduct(Mockito.anyString())
            Mockito.verify(remoteStore).fetchProductById(Mockito.anyString())
            Mockito.verify(localStore, Mockito.times(0)).updateProduct(mockProduct)
            Mockito.verify(localStore).getProductById(Mockito.anyString())
            assertEquals(mockProductWithCurrencyQuery, actual)
        }
    }

    @Test
    fun `validate fetch product with no local or remote stores`() {
        runBlocking {
            Mockito.`when`(
                remoteStore.fetchProductById(Mockito.anyString())
            ).thenReturn(
                null
            )
            Mockito.`when`(
                localStore.getProductById(Mockito.anyString())
            ).thenReturn(
                null
            )
            val actual = repository.fetchProduct(Mockito.anyString())
            Mockito.verify(remoteStore).fetchProductById(Mockito.anyString())
            Mockito.verify(localStore, Mockito.times(0)).storeProduct(mockProduct)
            Mockito.verify(localStore).getProductById(Mockito.anyString())
            Assert.assertEquals(null, actual)
        }
    }

}