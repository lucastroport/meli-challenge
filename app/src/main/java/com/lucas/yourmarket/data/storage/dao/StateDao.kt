package com.lucas.yourmarket.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.yourmarket.domain.model.State

@Dao
interface StateDao {

    @Query("SELECT * FROM ${State.TABLE_NAME} WHERE ${State.FIELD_ID}=:id")
    suspend fun getById(id: String): State?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(state: State)

    @Query("DELETE FROM ${State.TABLE_NAME}")
    suspend fun clearAll()
}