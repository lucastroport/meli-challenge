package com.lucas.yourmarket.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucas.yourmarket.data.model.Currency
import com.lucas.yourmarket.data.model.Product
import com.lucas.yourmarket.data.model.State
import com.lucas.yourmarket.data.model.User
import com.lucas.yourmarket.data.model.key.YourMarketRemoteKey
import com.lucas.yourmarket.data.storage.Converters
import com.lucas.yourmarket.data.storage.dao.*

@Database(
    entities = [
        YourMarketRemoteKey::class,
        Product::class,
        User::class,
        State::class,
        Currency::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class YourMarketDb : RoomDatabase(){

    companion object {
        internal const val DATABASE_NAME = "yourmarketdb"
    }

    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
    abstract fun stateDao(): StateDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun youMarketRemoteKeyDao(): YourMarketRemoteKeyDao
}