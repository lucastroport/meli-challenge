package com.lucas.yourmarket.data.remote.datastore

import com.lucas.yourmarket.data.remote.datastore.implementations.UserRemoteDatastoreImpl
import com.lucas.yourmarket.data.remote.datastore.interfaces.UserRemoteDatastore
import com.lucas.yourmarket.data.remote.services.UserService
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

class UserRemoteDataStoreTest {

    private val service = Mockito.mock(UserService::class.java)
    private val datastore: UserRemoteDatastore = UserRemoteDatastoreImpl(service)

    companion object {
        private val userMock = User(
            id = 1,
            nickname = "nick1",
            address = Address(city = "city1", state = "state1"),
            sellerReputation = SellerReputation(levelId = "levelId1", powerSellerStatus = "ps1")
        )
    }

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `should fetch user with network`() {
        runBlocking {
            Mockito.`when`(
                service.fetchUser(Mockito.anyLong())
            ).thenReturn(
                userMock
            )
            val actual = datastore.fetchUserById(Mockito.anyLong())
            Mockito.verify(service).fetchUser(Mockito.anyLong())
            assertEquals(userMock, actual)
        }
    }

    @Test
    fun `should fetch user without network`() {
        runBlocking {
            Mockito.`when`(
                service.fetchUser(Mockito.anyLong())
            ).thenThrow(
                RuntimeException()
            )
            val actual = datastore.fetchUserById(Mockito.anyLong())
            Mockito.verify(service).fetchUser(Mockito.anyLong())
            Assert.assertEquals(null, actual)
        }
    }
}