package com.lucas.yourmarket.data.model.response

import android.os.Parcelable
import com.lucas.yourmarket.data.model.Product
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsPagingResponse(
    val query: String,
    val paging: PagingData,
    val results: List<Product>
) : Parcelable
