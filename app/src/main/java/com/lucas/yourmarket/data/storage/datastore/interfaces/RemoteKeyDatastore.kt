package com.lucas.yourmarket.data.storage.datastore.interfaces

import com.lucas.yourmarket.domain.model.key.YourMarketRemoteKey

interface RemoteKeyDatastore {
    suspend fun getRemoteKeyById(id: String): YourMarketRemoteKey?
    suspend fun storeRemoteKeys(keys: List<YourMarketRemoteKey>)
    suspend fun clearAllRemoteKeys()
}
