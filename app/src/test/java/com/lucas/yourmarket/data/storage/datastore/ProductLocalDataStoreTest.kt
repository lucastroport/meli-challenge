package com.lucas.yourmarket.data.storage.datastore

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lucas.yourmarket.data.storage.dao.ProductDao
import com.lucas.yourmarket.data.storage.datastore.implementations.ProductLocalDatastoreImpl
import com.lucas.yourmarket.data.storage.datastore.interfaces.ProductLocalDatastore
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery
import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.domain.model.Shipping
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import kotlin.test.assertEquals

class ProductLocalDataStoreTest {

    private val dao = Mockito.mock(ProductDao::class.java)
    private val datastore: ProductLocalDatastore = ProductLocalDatastoreImpl(dao)

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

        private class MockPagingSource: PagingSource<Int, ProductWithCurrencyQuery>() {
            override fun getRefreshKey(state: PagingState<Int, ProductWithCurrencyQuery>): Int? {
                TODO("Not implemented")
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductWithCurrencyQuery> {
                TODO("Not implemented")
            }
        }
        private fun mockedPagingSource(): PagingSource<Int, ProductWithCurrencyQuery> = MockPagingSource()
    }



    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `should fetch product`() {
        runBlocking {
            Mockito.`when`(
                dao.getById(Mockito.anyString())
            ).thenReturn(
                mockProductWithCurrencyQuery
            )
            val actual = datastore.getProductById(Mockito.anyString())
            Mockito.verify(dao).getById(Mockito.anyString())
            assertEquals(mockProductWithCurrencyQuery, actual)
        }
    }

    @Test
    fun `should fetch product with error`() {
        runBlocking {
            Mockito.`when`(
                dao.getById(Mockito.anyString())
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.getProductById(Mockito.anyString())
            Mockito.verify(dao).getById(Mockito.anyString())
            Assert.assertEquals(null, actual)
        }
    }

    @Test
    fun `should fetch all products`() {
        val expected = mockedPagingSource()
        runBlocking {
            Mockito.`when`(
                dao.getPagingSource()
            ).thenReturn(
                expected
            )
            val actual = datastore.getProducts()
            Mockito.verify(dao).getPagingSource()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should store product`() {
        runBlocking {
            datastore.storeProduct(mockProduct)
            Mockito.verify(dao).insert(mockProduct)
        }
    }

    @Test
    fun `should store products`() {
        runBlocking {
            val products = listOf(mockProduct)
            datastore.storeProducts(products)
            Mockito.verify(dao).insertAll(products)
        }
    }

    @Test
    fun `should update product`() {
        runBlocking {
            datastore.updateProduct(mockProduct)
            Mockito.verify(dao).update(
                mockProduct.id,
                mockProduct.pictures,
                mockProduct.warranty ?: "",
                mockProduct.sellerId ?: 1L
            )
        }
    }

    @Test
    fun `should clear storage`() {
        runBlocking {
            datastore.clearAllProducts()
            Mockito.verify(dao).clearAll()
        }
    }
}
