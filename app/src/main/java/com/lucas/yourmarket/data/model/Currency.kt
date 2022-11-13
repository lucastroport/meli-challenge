package com.lucas.yourmarket.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Currency.TABLE_NAME)
data class Currency(
    @PrimaryKey
    @ColumnInfo(name = FIELD_ID)
    @SerializedName(FIELD_ID)
    val id: String,
    @ColumnInfo(name = FIELD_SYMBOL)
    @SerializedName(FIELD_SYMBOL)
    val symbol: String?,
    @ColumnInfo(name = FIELD_DESCRIPTION)
    @SerializedName(FIELD_DESCRIPTION)
    val description: String?,
    @ColumnInfo(name = FIELD_DECIMAL_PLACES)
    @SerializedName(FIELD_DECIMAL_PLACES)
    val decimalPlaces: Int?
) : Parcelable, BaseModel(){

    companion object {
        internal const val TABLE_NAME = "currency"
        internal const val FIELD_ID = "id"
        internal const val FIELD_SYMBOL = "symbol"
        internal const val FIELD_DESCRIPTION = "description"
        internal const val FIELD_DECIMAL_PLACES = "decimal_places"
    }

    override fun getEntityName(): String = TABLE_NAME

    override fun mandatoryFields(): Map<String, Any?> = mapOf(
        Pair(FIELD_ID, id),
        Pair(FIELD_SYMBOL, symbol)
    )
}
