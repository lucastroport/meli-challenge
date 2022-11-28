package com.lucas.yourmarket.data.storage.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lucas.yourmarket.data.storage.db.YourMarketDb
import com.lucas.yourmarket.domain.model.State
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
class StateDaoTest {

    private lateinit var stateDao: StateDao
    private lateinit var db: YourMarketDb

    companion object {
        private val statesMocks = listOf(
            State(
                id = "1",
                name = "name1"
            ),
            State(
                id = "2",
                name = "name2"
            )
        )
    }

    @Before
    fun createDb() {
        stopKoin()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, YourMarketDb::class.java).build()
        stateDao = db.stateDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        stopKoin()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `should insert and fetch state`() {
        runBlocking {
            val state = statesMocks[0]
            stateDao.insert(state)
            val actual = stateDao.getById(state.id)
            MatcherAssert.assertThat(actual, CoreMatchers.equalTo(state))
        }
    }

    @Test
    @Throws(Exception::class)
    fun `validate delete states`() {
        runBlocking {
            statesMocks.forEach {
                stateDao.insert(it)
                val actual = stateDao.getById(it.id)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(it))
            }

            stateDao.clearAll()
            statesMocks.forEach {
                val actual = stateDao.getById(it.id)
                MatcherAssert.assertThat(actual, CoreMatchers.equalTo(null))
            }
        }
    }
}
