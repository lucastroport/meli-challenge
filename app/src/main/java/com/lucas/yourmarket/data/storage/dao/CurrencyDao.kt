package com.lucas.yourmarket.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.yourmarket.domain.model.Currency
import com.lucas.yourmarket.domain.model.User

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM ${Currency.TABLE_NAME} WHERE ${Currency.FIELD_ID}=:id")
    suspend fun getById(id: String): Currency?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<Currency>)

    @Query("DELETE FROM ${Currency.TABLE_NAME}")
    suspend fun clearAll()
}
