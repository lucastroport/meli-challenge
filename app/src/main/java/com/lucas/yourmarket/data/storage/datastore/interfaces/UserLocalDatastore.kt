package com.lucas.yourmarket.data.storage.datastore.interfaces

import com.lucas.yourmarket.domain.model.User

interface UserLocalDatastore {
    suspend fun getUserById(id: Long): User?
    suspend fun storeUser(user: User)
    suspend fun clearAllUsers()
}