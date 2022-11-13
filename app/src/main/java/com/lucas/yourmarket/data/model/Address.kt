package com.lucas.yourmarket.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    @ColumnInfo(name = FIELD_CITY)
    @SerializedName(FIELD_CITY)
    val city: String?,
    @ColumnInfo(name = FIELD_STATE)
    @SerializedName(FIELD_STATE)
    val state: String?
) : Parcelable {

    companion object {
        internal const val FIELD_CITY = "city"
        internal const val FIELD_STATE = "state"
    }

}
