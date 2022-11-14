package com.lucas.yourmarket.data.repository.implementations

import com.lucas.yourmarket.data.model.User
import com.lucas.yourmarket.data.remote.datastore.interfaces.UserRemoteDatastore
import com.lucas.yourmarket.data.repository.interfaces.UserRepository
import com.lucas.yourmarket.data.storage.datastore.interfaces.UserLocalDatastore

class UserRepositoryImpl(
    private val userLocalDatastore: UserLocalDatastore,
    private val userRemoteDatastore: UserRemoteDatastore
): UserRepository {
    override suspend fun fetchUser(userId: Long): User? {
        val response = userRemoteDatastore.fetchUserById(userId)
        response?.let {
            userLocalDatastore.storeUser(it)
        }
        return userLocalDatastore.getUserById(userId)
    }
}
