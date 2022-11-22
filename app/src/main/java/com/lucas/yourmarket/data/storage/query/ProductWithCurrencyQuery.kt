package com.lucas.yourmarket.data.storage.query

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.lucas.yourmarket.domain.model.BaseModel
import com.lucas.yourmarket.domain.model.Picture
import com.lucas.yourmarket.domain.model.Product
import com.lucas.yourmarket.domain.model.Shipping
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductWithCurrencyQuery(
    val id: String,
    val title: String?,
    val price: Double?,
    val condition: String?,
    val thumbnail: String?,
    val warranty: String?,
    val currency: String?,
    val pictures: List<Picture>?,
    @ColumnInfo(name = Shipping.FIELD_FREE_SHIPPING)
    val freeShipping: Boolean?,
    @ColumnInfo(name = Product.FIELD_SELLER_ID)
    val sellerId: Long?,
    @ColumnInfo(name = Product.FIELD_SOLD_QUANTITY)
    val soldQuantity: Int?
): Parcelable, BaseModel() {

    override fun getEntityName(): String = ENTITY_NAME

    override fun mandatoryFields(): Map<String, Any?> = mapOf(
        Pair(Product.FIELD_ID, id),
        Pair(Product.FIELD_TITLE, title),
        Pair(Product.FIELD_PRICE, price),
        Pair(Product.FIELD_SELLER_ID, sellerId)
    )

    companion object {
        internal const val ENTITY_NAME = "ProductWithCurrencyQuery"
    }

}
