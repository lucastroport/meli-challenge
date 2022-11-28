package com.lucas.yourmarket.data.storage.datastore

import com.lucas.yourmarket.data.storage.dao.UserDao
import com.lucas.yourmarket.data.storage.datastore.implementations.UserLocalDatastoreImpl
import com.lucas.yourmarket.data.storage.datastore.interfaces.UserLocalDatastore
import com.lucas.yourmarket.domain.model.Address
import com.lucas.yourmarket.domain.model.SellerReputation
import com.lucas.yourmarket.domain.model.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import kotlin.test.assertEquals

class UserLocalDataStoreTest {

    private val dao = Mockito.mock(UserDao::class.java)
    private val datastore: UserLocalDatastore = UserLocalDatastoreImpl(dao)

    @Before
    fun setUp() {
        stopKoin()
    }

    companion object {
        private val userMock = User(
            id = 1,
            nickname = "nick1",
            address = Address(city = "city1", state = "state1"),
            sellerReputation = SellerReputation(levelId = "levelId1", powerSellerStatus = "ps1")
        )
    }

    @Test
    fun `should fetch user`() {
        runBlocking {
            Mockito.`when`(
                dao.getById(Mockito.anyLong())
            ).thenReturn(
                userMock
            )
            val actual = datastore.getUserById(Mockito.anyLong())
            Mockito.verify(dao).getById(Mockito.anyLong())
            assertEquals(userMock, actual)
        }
    }

    @Test
    fun `should fetch user with error`() {
        runBlocking {
            Mockito.`when`(
                dao.getById(Mockito.anyLong())
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.getUserById(Mockito.anyLong())
            Mockito.verify(dao).getById(Mockito.anyLong())
            Assert.assertEquals(null, actual)
        }
    }

    @Test
    fun `should store user`() {
        runBlocking {
            datastore.storeUser(userMock)
            Mockito.verify(dao).insert(userMock)
        }
    }

    @Test
    fun `validate clear storage`() {
        runBlocking {
            datastore.clearAllUsers()
            Mockito.verify(dao).clearAll()
        }
    }
}
