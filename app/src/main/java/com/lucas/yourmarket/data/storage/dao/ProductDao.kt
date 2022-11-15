package com.lucas.yourmarket.data.storage.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.yourmarket.domain.model.Currency
import com.lucas.yourmarket.domain.model.Picture
import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.data.storage.query.ProductWithCurrencyQuery

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Query("DELETE FROM ${Product.TABLE_NAME}")
    suspend fun clearAll()

    @Query("UPDATE ${Product.TABLE_NAME} SET ${Product.FIELD_PICTURES} = :pictures, ${Product.FIELD_WARRANTY} = :warranty, ${Product.FIELD_SELLER_ID} = :sellerId WHERE ${Product.FIELD_ID} = :id")
    suspend fun update(id: String, pictures: List<Picture>, warranty: String, sellerId: Long)

    @Query("SELECT t1.*, t2.symbol as currency FROM ${Product.TABLE_NAME} t1 LEFT JOIN ${Currency.TABLE_NAME} t2 ON t1.${Product.FIELD_CURRENCY_ID}=t2.${Currency.FIELD_ID} WHERE t1.${Product.FIELD_ID}=:id")
    suspend fun getById(id: String): ProductWithCurrencyQuery?

    @Query("SELECT t1.*, t2.symbol as currency FROM ${Product.TABLE_NAME} t1 LEFT JOIN ${Currency.TABLE_NAME} t2 ON t1.${Product.FIELD_CURRENCY_ID}=t2.${Currency.FIELD_ID}")
    fun getPagingSource(): PagingSource<Int, ProductWithCurrencyQuery>?
}