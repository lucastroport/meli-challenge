package com.lucas.yourmarket.data.storage.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lucas.yourmarket.data.storage.db.YourMarketDb
import com.lucas.yourmarket.domain.model.Currency
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
class CurrencyDaoTest {

    private lateinit var currencyDao: CurrencyDao
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
    }

    @Before
    fun initDatabase() {
        stopKoin()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, YourMarketDb::class.java).build()
        currencyDao = db.currencyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        stopKoin()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `validate currency insertion and retrieval`() {
        runBlocking {
            currencyDao.insertAll(currenciesMock)
            currenciesMock.forEach {
                val actual = currencyDao.getById(it.id)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(it))
            }
        }
    }
}