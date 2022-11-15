package com.lucas.yourmarket.domain.repository.interfaces

import com.lucas.yourmarket.domain.model.User

interface UserRepository {
    suspend fun fetchUser(userId: Long): User?
}