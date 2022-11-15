package com.lucas.yourmarket.domain.model.response

import android.os.Parcelable
import com.lucas.yourmarket.domain.model.Product
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsPagingResponse(
    val query: String,
    val paging: PagingData,
    val results: List<Product>
) : Parcelable
