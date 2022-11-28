package com.lucas.yourmarket.data.storage.datastore

import com.lucas.yourmarket.data.storage.dao.StateDao
import com.lucas.yourmarket.data.storage.datastore.implementations.StateLocalDatastoreImpl
import com.lucas.yourmarket.data.storage.datastore.interfaces.StateLocalDatastore
import com.lucas.yourmarket.domain.model.State
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import kotlin.test.assertEquals

class StateLocalDataStoreTest {

    private val dao = Mockito.mock(StateDao::class.java)
    private val datastore: StateLocalDatastore = StateLocalDatastoreImpl(dao)

    companion object {
        private val stateMock = State(
            id = "1",
            name = "name1"
        )
    }

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `should fetch state`() {
        runBlocking {
            Mockito.`when`(
                dao.getById(Mockito.anyString())
            ).thenReturn(
                stateMock
            )
            val actual = datastore.getStateById(Mockito.anyString())
            Mockito.verify(dao).getById(Mockito.anyString())
            assertEquals(stateMock, actual)
        }
    }

    @Test
    fun `should fetch state with error`() {
        runBlocking {
            Mockito.`when`(
                dao.getById(Mockito.anyString())
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.getStateById(Mockito.anyString())
            Mockito.verify(dao).getById(Mockito.anyString())
            Assert.assertEquals(null, actual)
        }
    }

    @Test
    fun `should store state`() {
        runBlocking {
            val state = stateMock
            datastore.storeState(state)
            Mockito.verify(dao).insert(state)
        }
    }

    @Test
    fun `should clear storage`() {
        runBlocking {
            datastore.clearAllStates()
            Mockito.verify(dao).clearAll()
        }
    }
}
