package com.lucas.yourmarket.data.storage.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lucas.yourmarket.data.storage.db.YourMarketDb
import com.lucas.yourmarket.domain.model.Address
import com.lucas.yourmarket.domain.model.SellerReputation
import com.lucas.yourmarket.domain.model.User
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
class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var db: YourMarketDb

    companion object {
        private val usersMocks = listOf(
            User(
                id = 1,
                nickname = "nick1",
                address = Address(city = "city1", state = "state1"),
                sellerReputation = SellerReputation(levelId = "levelId1", powerSellerStatus = "ps1")
            ),
            User(
                id = 2,
                nickname = "nick2",
                address = Address(city = "city2", state = "state2"),
                sellerReputation = SellerReputation(levelId = "levelId2", powerSellerStatus = "ps2")
            )
        )
    }

    @Before
    fun createDb() {
        stopKoin()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, YourMarketDb::class.java).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        stopKoin()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `should insert and fetch user`() {
        runBlocking {
            val user = usersMocks[0]
            userDao.insert(user)
            val actual = userDao.getById(user.id)
            MatcherAssert.assertThat(actual, CoreMatchers.equalTo(user))
        }
    }

    @Test
    @Throws(Exception::class)
    fun `should delete users`() {
        runBlocking {
            usersMocks.forEach {
                userDao.insert(it)
                val actual = userDao.getById(it.id)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(it))
            }

            userDao.clearAll()
            usersMocks.forEach {
                val actual = userDao.getById(it.id)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(null))
            }
        }
    }
}
