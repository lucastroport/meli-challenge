package com.lucas.yourmarket.data.storage.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lucas.yourmarket.data.storage.db.YourMarketDb
import com.lucas.yourmarket.domain.model.key.YourMarketRemoteKey
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
class RemoteKeyDaoTest {
    private lateinit var remoteKeyDao: YourMarketRemoteKeyDao
    private lateinit var db: YourMarketDb

    companion object {
        private val remoteKeysMock = listOf(
            YourMarketRemoteKey(
                id = "page1",
                prevPage = 0,
                nextPage = 0
            ),
            YourMarketRemoteKey(
                id = "page2",
                prevPage = 0,
                nextPage = 0
            ),
        )
    }

    @Before
    fun createDb() {
        stopKoin()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, YourMarketDb::class.java).build()
        remoteKeyDao = db.youMarketRemoteKeyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        stopKoin()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `should insert and fetch remote key`() {
        runBlocking {
            remoteKeyDao.insertAll(remoteKeysMock)
            remoteKeysMock.forEach {
                val actual = remoteKeyDao.getById(it.id)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(it))
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun `should delete remote keys`() {
        runBlocking {
            remoteKeyDao.insertAll(remoteKeysMock)
            remoteKeysMock.forEach {
                val actual = remoteKeyDao.getById(it.id)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(it))
            }

            remoteKeyDao.clearAll()
            remoteKeysMock.forEach {
                val actual = remoteKeyDao.getById(it.id)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(null))
            }
        }
    }
}
