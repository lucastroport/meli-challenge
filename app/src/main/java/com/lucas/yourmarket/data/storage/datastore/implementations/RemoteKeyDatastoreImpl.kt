package com.lucas.yourmarket.data.storage.datastore.implementations

import android.util.Log
import com.lucas.yourmarket.data.model.key.YourMarketRemoteKey
import com.lucas.yourmarket.data.storage.dao.YourMarketRemoteKeyDao
import com.lucas.yourmarket.data.storage.datastore.interfaces.RemoteKeyDatastore

class RemoteKeyDatastoreImpl(
    private val remoteKeyDao: YourMarketRemoteKeyDao
): RemoteKeyDatastore {

    companion object {
        private val CLASS_TAG = RemoteKeyDatastoreImpl::class.qualifiedName
    }

    override suspend fun getRemoteKeyById(id: String): YourMarketRemoteKey? {
        return try {
            remoteKeyDao.getById(id)
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
            null
        }
    }

    override suspend fun storeRemoteKeys(keys: List<YourMarketRemoteKey>) {
        try {
            remoteKeyDao.insertAll(keys)
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
        }
    }

    override suspend fun clearAllRemoteKeys() {
        try {
            remoteKeyDao.clearAll()
        } catch (e: Exception) {
            Log.e(CLASS_TAG, Log.getStackTraceString(e))
        }
    }
}