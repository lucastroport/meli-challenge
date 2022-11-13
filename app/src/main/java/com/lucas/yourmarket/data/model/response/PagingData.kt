package com.lucas.yourmarket.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PagingData(
    val total: Long,
    val offset: Int,
    val limit: Int,
    @SerializedName("primary_results")
    val primaryResults: Int
) : Parcelable
