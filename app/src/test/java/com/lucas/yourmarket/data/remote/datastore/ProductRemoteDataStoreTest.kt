package com.lucas.yourmarket.data.remote.datastore

import com.lucas.yourmarket.BuildConfig
import com.lucas.yourmarket.data.remote.datastore.implementations.ProductRemoteDatastoreImpl
import com.lucas.yourmarket.data.remote.datastore.interfaces.ProductRemoteDatastore
import com.lucas.yourmarket.data.remote.services.ProductService
import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.domain.model.Shipping
import com.lucas.yourmarket.domain.model.response.PagingData
import com.lucas.yourmarket.domain.model.response.ProductsPagingResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import kotlin.test.assertEquals

class ProductRemoteDataStoreTest {

    private val service = Mockito.mock(ProductService::class.java)
    private val datastore: ProductRemoteDatastore = ProductRemoteDatastoreImpl(service)

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

        private val mockProductsPage = ProductsPagingResponse("",
            PagingData(0L, 0, 0, 0),
            listOf(mockProduct))
    }

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `should fetch product without network`() {
        runBlocking {
            Mockito.`when`(
                service.fetchProduct(Mockito.anyString())
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.fetchProductById(Mockito.anyString())
            Mockito.verify(service).fetchProduct(Mockito.anyString())
            Assert.assertEquals(null, actual)
        }
    }

    @Test
    fun `validate fetch products page with network`() {
        val siteId = Mockito.anyString()
        val query = Mockito.anyString()
        val offset = Mockito.anyInt()
        val limit = Mockito.anyInt()
        runBlocking {
            Mockito.`when`(
                service.fetchProductsPage(
                    siteId,
                    query,
                    offset,
                    limit
                )
            ).thenReturn(
                mockProductsPage
            )
            val actual = datastore.fetchProducts(query, offset, limit)
            Mockito.verify(service).fetchProductsPage(BuildConfig.MELI_SITE_ID, query, offset, limit)
            assertEquals(mockProductsPage, actual)
        }
    }

    @Test
    fun `validate fetch products page without network`() {
        val siteId = Mockito.anyString()
        val query = Mockito.anyString()
        val offset = Mockito.anyInt()
        val limit = Mockito.anyInt()
        runBlocking {
            Mockito.`when`(
                service.fetchProductsPage(siteId, query, offset, limit)
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.fetchProducts(query, offset, limit)
            Mockito.verify(service).fetchProductsPage(BuildConfig.MELI_SITE_ID, query, offset, limit)
            Assert.assertEquals(null, actual)
        }
    }
}
