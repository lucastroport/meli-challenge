package com.lucas.yourmarket.data.remote.datastore

import com.lucas.yourmarket.data.remote.datastore.implementations.StateRemoteDatastoreImpl
import com.lucas.yourmarket.data.remote.datastore.interfaces.StateRemoteDatastore
import com.lucas.yourmarket.data.remote.services.StateService
import com.lucas.yourmarket.domain.model.State
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import kotlin.test.assertEquals

class StateRemoteDataStoreTest {

    private val service = Mockito.mock(StateService::class.java)
    private val datastore: StateRemoteDatastore = StateRemoteDatastoreImpl(service)

    @Before
    fun setUp() {
        stopKoin()
    }

    companion object {
        private val stateMock = State(
            id = "1",
            name = "name1"
        )
    }

    @Test
    fun `should fetch state with network`() {
        runBlocking {
            Mockito.`when`(
                service.fetchState(Mockito.anyString())
            ).thenReturn(
                stateMock
            )
            val actual = datastore.getStateById(Mockito.anyString())
            Mockito.verify(service).fetchState(Mockito.anyString())
            assertEquals(stateMock, actual)
        }
    }

    @Test
    fun `should fetch state without network`() {
        runBlocking {
            Mockito.`when`(
                service.fetchState(Mockito.anyString())
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.getStateById(Mockito.anyString())
            Mockito.verify(service).fetchState(Mockito.anyString())
            Assert.assertEquals(null, actual)
        }
    }

}