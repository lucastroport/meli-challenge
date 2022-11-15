package com.lucas.yourmarket.domain.model.key

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lucas.yourmarket.domain.model.key.YourMarketRemoteKey.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class YourMarketRemoteKey(
    @PrimaryKey
    @ColumnInfo(name = FIELD_ID)
    val id: String,
    @ColumnInfo(name = FIELD_PREV_KEY)
    val prevPage: Int?,
    @ColumnInfo(name = FIELD_NEXT_KEY)
    val nextPage: Int?
) {
    companion object {
        internal const val TABLE_NAME = "yourmarket_remote_Key"
        internal const val FIELD_ID = "id"
        internal const val FIELD_PREV_KEY = "prev_key"
        internal const val FIELD_NEXT_KEY = "next_key"
    }
}
