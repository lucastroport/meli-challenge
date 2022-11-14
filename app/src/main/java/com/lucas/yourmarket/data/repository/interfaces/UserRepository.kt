package com.lucas.yourmarket.data.repository.interfaces

import com.lucas.yourmarket.data.model.User

interface UserRepository {
    suspend fun fetchUser(userId: Long): User?
}