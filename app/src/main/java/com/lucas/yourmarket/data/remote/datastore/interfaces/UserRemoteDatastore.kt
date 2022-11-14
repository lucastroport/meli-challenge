package com.lucas.yourmarket.data.remote.datastore.interfaces

import com.lucas.yourmarket.data.model.User

interface UserRemoteDatastore {
    suspend fun fetchUserById(userId: Long): User?
}