package com.lucas.yourmarket.domain.repository

import com.lucas.yourmarket.data.remote.datastore.interfaces.UserRemoteDatastore
import com.lucas.yourmarket.data.storage.datastore.interfaces.UserLocalDatastore
import com.lucas.yourmarket.domain.model.Address
import com.lucas.yourmarket.domain.model.SellerReputation
import com.lucas.yourmarket.domain.model.User
import com.lucas.yourmarket.domain.repository.implementations.UserRepositoryImpl
import com.lucas.yourmarket.domain.repository.interfaces.UserRepository
import com.lucas.yourmarket.test.BaseTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import kotlin.test.assertEquals

class UserRepositoryTest : BaseTest() {

    private val localStore = Mockito.mock(UserLocalDatastore::class.java)
    private val remoteStore = Mockito.mock(UserRemoteDatastore::class.java)
    private val repository: UserRepository = UserRepositoryImpl(localStore, remoteStore)

    companion object {
        private val userMock = User(
            id = 1,
            nickname = "test nickname",
            address = Address(city = "test city", state = "some state"),
            sellerReputation = SellerReputation(levelId = "some level", powerSellerStatus = "some status")
        )
    }

    @Before
    fun setUp() {
        stopKoin()
    }

    @Test
    fun `validate fetch user with local and remote datastore`() {
        runBlocking {
            Mockito.`when`(
                remoteStore.fetchUserById(anyLong())
            ).thenReturn(
                userMock
            )
            Mockito.`when`(
                localStore.getUserById(anyLong())
            ).thenReturn(
                userMock
            )
            val actual = repository.fetchUser(anyLong())
            Mockito.verify(remoteStore).fetchUserById(anyLong())
            Mockito.verify(localStore).storeUser(userMock)
            Mockito.verify(localStore).getUserById(anyLong())
            assertEquals(userMock, actual)
        }
    }

    @Test
    fun `validate fetch user with local store only`() {
        runBlocking {
            Mockito.`when`(
                remoteStore.fetchUserById(anyLong())
            ).thenReturn(
                null
            )
            Mockito.`when`(
                localStore.getUserById(anyLong())
            ).thenReturn(
                userMock
            )
            val actual = repository.fetchUser(anyLong())
            Mockito.verify(remoteStore).fetchUserById(anyLong())
            Mockito.verify(localStore, Mockito.times(0)).storeUser(userMock)
            Mockito.verify(localStore).getUserById(anyLong())
            assertEquals(userMock, actual)
        }
    }

    @Test
    fun `validate fetch user with no local or remote stores`() {
        runBlocking {
            Mockito.`when`(
                remoteStore.fetchUserById(Mockito.anyLong())
            ).thenReturn(
                null
            )
            Mockito.`when`(
                localStore.getUserById(Mockito.anyLong())
            ).thenReturn(
                null
            )
            val actual = repository.fetchUser(Mockito.anyLong())
            Mockito.verify(remoteStore).fetchUserById(Mockito.anyLong())
            Mockito.verify(localStore, Mockito.times(0)).storeUser(userMock)
            Mockito.verify(localStore).getUserById(Mockito.anyLong())
            Assert.assertEquals(null, actual)
        }
    }
}
