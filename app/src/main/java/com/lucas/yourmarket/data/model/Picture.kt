package com.lucas.yourmarket.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(
    @ColumnInfo(name = FIELD_SECURE_URL)
    @SerializedName(FIELD_SECURE_URL)
    val url: String?
) : Parcelable {

    companion object {
        internal const val FIELD_SECURE_URL = "secure_url"
    }
}
