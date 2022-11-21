package com.lucas.yourmarket.data.storage.db

import android.content.Context
import androidx.room.Room

fun provideDatabase(context: Context): YourMarketDb {
    return Room.databaseBuilder(
        context.applicationContext,
        YourMarketDb::class.java,
        YourMarketDb.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}
