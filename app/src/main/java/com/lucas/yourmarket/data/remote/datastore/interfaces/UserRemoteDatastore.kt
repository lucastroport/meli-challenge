package com.lucas.yourmarket.data.remote.datastore.interfaces

import com.lucas.yourmarket.domain.model.User

interface UserRemoteDatastore {
    suspend fun fetchUserById(userId: Long): User?
}