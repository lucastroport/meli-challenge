package com.lucas.yourmarket.domain.repository

import com.lucas.yourmarket.data.remote.datastore.interfaces.StateRemoteDatastore
import com.lucas.yourmarket.data.storage.datastore.interfaces.StateLocalDatastore
import com.lucas.yourmarket.domain.model.State
import com.lucas.yourmarket.domain.repository.implementations.StateRepositoryImpl
import com.lucas.yourmarket.domain.repository.interfaces.StateRepository
import com.lucas.yourmarket.test.BaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import kotlin.test.assertEquals

class StateRepositoryTest : BaseTest() {

    private val localStore = Mockito.mock(StateLocalDatastore::class.java)
    private val remoteStore = Mockito.mock(StateRemoteDatastore::class.java)
    private val repository: StateRepository = StateRepositoryImpl(localStore, remoteStore)

    companion object {
        private val stateMock = State(
            id = "1",
            name = "some"
        )
    }

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `validate fetch state with local and remote datastore`() {
        runBlocking {
            Mockito.`when`(
                remoteStore.getStateById(anyString())
            ).thenReturn(
                stateMock
            )
            Mockito.`when`(
                localStore.getStateById(anyString())
            ).thenReturn(
                stateMock
            )
            val actual = repository.fetchState(anyString())
            Mockito.verify(remoteStore).getStateById(anyString())
            Mockito.verify(localStore).storeState(stateMock)
            Mockito.verify(localStore, Mockito.times(0)).getStateById(anyString())
            assertEquals(stateMock, actual)
        }
    }


    @Test
    fun `validate fetch state with local store only`() {
        runBlocking {
            Mockito.`when`(
                remoteStore.getStateById(anyString())
            ).thenReturn(
                null
            )
            Mockito.`when`(
                localStore.getStateById(anyString())
            ).thenReturn(
                stateMock
            )
            val actual = repository.fetchState(anyString())
            Mockito.verify(remoteStore).getStateById(anyString())
            Mockito.verify(localStore, Mockito.times(0)).storeState(stateMock)
            Mockito.verify(localStore).getStateById(anyString())
            assertEquals(stateMock, actual)
        }
    }

    @Test
    fun `validate fetch state with no local or remote stores`() {
        runBlocking {
            Mockito.`when`(
                remoteStore.getStateById(anyString())
            ).thenReturn(
                null
            )
            Mockito.`when`(
                localStore.getStateById(anyString())
            ).thenReturn(
                null
            )
            val actual = repository.fetchState(anyString())
            Mockito.verify(remoteStore).getStateById(anyString())
            Mockito.verify(localStore, Mockito.times(0)).storeState(stateMock)
            Mockito.verify(localStore).getStateById(anyString())
            Assert.assertEquals(null, actual)
        }
    }
}
