package com.lucas.yourmarket.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shipping(
    @ColumnInfo(name = FIELD_FREE_SHIPPING)
    @SerializedName(FIELD_FREE_SHIPPING)
    val freeShipping: Boolean?
): Parcelable {
    companion object {
        internal const val FIELD_FREE_SHIPPING = "free_shipping"
    }
}
