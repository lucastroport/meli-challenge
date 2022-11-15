package com.lucas.yourmarket.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.yourmarket.domain.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.FIELD_ID}=:id")
    suspend fun getById(id: Long): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("DELETE FROM ${User.TABLE_NAME}")
    suspend fun clearAll()
}
