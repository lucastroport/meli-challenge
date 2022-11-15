package com.lucas.yourmarket.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.yourmarket.domain.model.key.YourMarketRemoteKey

@Dao
interface YourMarketRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<YourMarketRemoteKey>)

    @Query("SELECT * FROM ${YourMarketRemoteKey.TABLE_NAME} WHERE ${YourMarketRemoteKey.FIELD_ID} = :id")
    suspend fun getById(id: String): YourMarketRemoteKey?

    @Query("DELETE FROM ${YourMarketRemoteKey.TABLE_NAME}")
    suspend fun clearAll()
}