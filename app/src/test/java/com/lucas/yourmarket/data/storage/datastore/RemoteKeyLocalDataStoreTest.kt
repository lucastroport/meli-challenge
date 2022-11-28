package com.lucas.yourmarket.data.storage.datastore

import com.lucas.yourmarket.data.storage.dao.YourMarketRemoteKeyDao
import com.lucas.yourmarket.data.storage.datastore.implementations.RemoteKeyDatastoreImpl
import com.lucas.yourmarket.data.storage.datastore.interfaces.RemoteKeyDatastore
import com.lucas.yourmarket.domain.model.key.YourMarketRemoteKey
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import kotlin.test.assertEquals

class RemoteKeyLocalDataStoreTest {
    private val dao = Mockito.mock(YourMarketRemoteKeyDao::class.java)
    private val datastore: RemoteKeyDatastore = RemoteKeyDatastoreImpl(dao)

    companion object {
        private val remoteKeyMock = YourMarketRemoteKey(
            id = "page1",
            prevPage = 0,
            nextPage = 0
        )
    }

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `should fetch remote key`() {
        runBlocking {
            Mockito.`when`(
                dao.getById(Mockito.anyString())
            ).thenReturn(
                remoteKeyMock
            )
            val actual = datastore.getRemoteKeyById(Mockito.anyString())
            Mockito.verify(dao).getById(Mockito.anyString())
            assertEquals(remoteKeyMock, actual)
        }
    }

    @Test
    fun `should fetch remote key with exception`() {
        runBlocking {
            Mockito.`when`(
                dao.getById(Mockito.anyString())
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.getRemoteKeyById(Mockito.anyString())
            Mockito.verify(dao).getById(Mockito.anyString())
            Assert.assertEquals(null, actual)
        }
    }

    @Test
    fun `should store remote key`() {
        runBlocking {
            val keys = listOf(remoteKeyMock)
            datastore.storeRemoteKeys(keys)
            Mockito.verify(dao).insertAll(keys)
        }
    }

    @Test
    fun `should clear storage`() {
        runBlocking {
            datastore.clearAllRemoteKeys()
            Mockito.verify(dao).clearAll()
        }
    }
}
