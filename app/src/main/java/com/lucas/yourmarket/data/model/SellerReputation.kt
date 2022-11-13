package com.lucas.yourmarket.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SellerReputation(
    @ColumnInfo(name = FIELD_LEVEL_ID)
    @SerializedName(FIELD_LEVEL_ID)
    val levelId: String?,
    @ColumnInfo(name = FIELD_POWER_SELLER_STATUS)
    @SerializedName(FIELD_POWER_SELLER_STATUS)
    val powerSellerStatus: String?
) : Parcelable {

    companion object {
        internal const val FIELD_LEVEL_ID = "level_id"
        internal const val FIELD_POWER_SELLER_STATUS = "power_seller_status"
        internal const val STATUS_TYPE_PLATINIUM = "platinum"
    }
}
