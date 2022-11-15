package com.lucas.yourmarket.data.remote.datastore.implementations

import android.util.Log
import com.lucas.yourmarket.domain.model.User
import com.lucas.yourmarket.data.remote.datastore.interfaces.UserRemoteDatastore
import com.lucas.yourmarket.data.remote.services.UserService

class UserRemoteDatastoreImpl(
    private val userService: UserService
): UserRemoteDatastore {
    companion object {
        private val CLASS_TAG = UserRemoteDatastoreImpl::class.qualifiedName
    }

    override suspend fun fetchUserById(userId: Long): User? {
        return try {
            userService.fetchUser(userId)
        }catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
            null
        }
    }

}