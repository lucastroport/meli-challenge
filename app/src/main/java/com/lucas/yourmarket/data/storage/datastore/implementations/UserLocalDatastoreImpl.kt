package com.lucas.yourmarket.data.storage.datastore.implementations

import android.util.Log
import com.lucas.yourmarket.data.model.User
import com.lucas.yourmarket.data.storage.dao.UserDao
import com.lucas.yourmarket.data.storage.datastore.interfaces.UserLocalDatastore

class UserLocalDatastoreImpl(
    private val userDao: UserDao
): UserLocalDatastore {

    companion object {
        private val CLASS_TAG = UserLocalDatastoreImpl::class.qualifiedName
    }

    override suspend fun getUserById(id: Long): User? {
        return try {
            userDao.getById(id)
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
            null
        }
    }

    override suspend fun storeUser(user: User) {
        try {
            userDao.insert(user)
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
        }
    }

    override suspend fun clearAllUsers() {
        try {
            userDao.clearAll()
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
        }
    }
}
