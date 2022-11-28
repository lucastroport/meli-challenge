package com.lucas.yourmarket.data.storage.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lucas.yourmarket.data.storage.db.YourMarketDb
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery
import com.lucas.yourmarket.domain.model.Currency
import com.lucas.yourmarket.domain.model.Picture
import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.domain.model.Shipping
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ProductDaoTest {

    private lateinit var currencyDao: CurrencyDao
    private lateinit var productDao: ProductDao
    private lateinit var db: YourMarketDb

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
    }

    @Before
    fun initDatabase() {
        stopKoin()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, YourMarketDb::class.java).build()
        productDao = db.productDao()
        currencyDao = db.currencyDao()
        runBlocking {
            currencyDao.insertAll(currenciesMock)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        stopKoin()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `should product fetch insert and update`() {
        runBlocking {
            val currency = currenciesMock.firstOrNull { it.id == mockProduct.currencyId }
            productDao.insert(mockProduct)

            val actual = productDao.getById(mockProduct.id)
            val expected = ProductWithCurrencyQuery(
                id = mockProduct.id,
                title = mockProduct.title,
                price = mockProduct.price,
                condition = mockProduct.condition,
                thumbnail = mockProduct.thumbnail,
                warranty = mockProduct.warranty,
                currency = currency?.symbol,
                pictures = mockProduct.pictures,
                freeShipping = mockProduct.freeShipping,
                sellerId = mockProduct.sellerId,
                soldQuantity = mockProduct.soldQuantity
            )
            MatcherAssert.assertThat(actual, CoreMatchers.equalTo(expected))

            val mockPictures = listOf(Picture(null), Picture(null))
            val mockWarranty = "warranty"
            val mockSellerId = 1234L
            productDao.update(
                id = mockProduct.id,
                pictures = mockPictures,
                warranty = mockWarranty,
                sellerId = mockSellerId
            )
            val actualUpdated = productDao.getById(mockProduct.id)
            MatcherAssert.assertThat(actualUpdated?.pictures, CoreMatchers.equalTo(mockPictures))
            MatcherAssert.assertThat(actualUpdated?.warranty, CoreMatchers.equalTo(mockWarranty))
            MatcherAssert.assertThat(actualUpdated?.sellerId, CoreMatchers.equalTo(mockSellerId))

            val actualTotal = productDao.getAll()
            MatcherAssert.assertThat(actualTotal.size, CoreMatchers.equalTo(1))
        }
    }

    @Test
    @Throws(Exception::class)
    fun `should insert and fetch products`() {
        runBlocking {
            val products = listOf(mockProduct)
            productDao.insertAll(products)

            for (product in products) {
                val currency = currenciesMock.firstOrNull { it.id == product.currencyId }
                val actual = productDao.getById(product.id)
                val expected = ProductWithCurrencyQuery(product.id, product.title,
                    product.price, product.condition, product.thumbnail,
                    product.warranty, currency?.symbol, product.pictures,
                    product.freeShipping, product.sellerId, soldQuantity = product.soldQuantity)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(expected))
            }

            val actual = productDao.getAll()
            MatcherAssert.assertThat(actual.size, CoreMatchers.equalTo(products.size))
        }
    }

    @Test
    @Throws(Exception::class)
    fun `should delete products`() {
        runBlocking {
            val products = listOf(mockProduct)

            productDao.insertAll(products)
            val actualInserted = productDao.getAll()
            MatcherAssert.assertThat(actualInserted.size, CoreMatchers.equalTo(products.size))

            productDao.clearAll()
            val actualDeleted = productDao.getAll()
            MatcherAssert.assertThat(actualDeleted.size, CoreMatchers.equalTo(0))
        }
    }
}
