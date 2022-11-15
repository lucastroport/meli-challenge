package com.lucas.yourmarket.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Product.TABLE_NAME)
data class Product(
    @PrimaryKey
    @ColumnInfo(name = FIELD_ID)
    @SerializedName(FIELD_ID)
    val id: String,
    @ColumnInfo(name = FIELD_TITLE)
    @SerializedName(FIELD_TITLE)
    val title: String?,
    @ColumnInfo(name = FIELD_PRICE)
    @SerializedName(FIELD_PRICE)
    val price: Double?,
    @ColumnInfo(name = FIELD_CONDITION)
    @SerializedName(FIELD_CONDITION)
    val condition: String?,
    @ColumnInfo(name = FIELD_THUMBNAIL)
    @SerializedName(FIELD_THUMBNAIL)
    private val _thumbnail: String?,
    @Embedded
    @SerializedName(FIELD_SHIPPING)
    val _shipping: Shipping,
    @ColumnInfo(name = FIELD_PICTURES)
    @SerializedName(FIELD_PICTURES)
    val pictures: List<Picture> = emptyList(),
    @ColumnInfo(name = FIELD_SELLER_ID)
    @SerializedName(FIELD_SELLER_ID)
    val sellerId: Long?,
    @ColumnInfo(name = FIELD_WARRANTY)
    @SerializedName(FIELD_WARRANTY)
    val warranty: String?,
    @ColumnInfo(name = FIELD_CURRENCY_ID)
    @SerializedName(FIELD_CURRENCY_ID)
    val currencyId: String?,
) : Parcelable, BaseModel() {

    companion object {
        private const val HTTP = "http://"
        private const val HTTPS = "https://"

        internal const val TABLE_NAME = "product"
        internal const val FIELD_ID = "id"
        internal const val FIELD_TITLE = "title"
        internal const val FIELD_PRICE = "price"
        internal const val FIELD_CONDITION = "condition"
        internal const val FIELD_THUMBNAIL = "thumbnail"
        internal const val FIELD_SHIPPING = "shipping"
        internal const val FIELD_PICTURES = "pictures"
        internal const val FIELD_SELLER_ID = "seller_id"
        internal const val FIELD_WARRANTY = "warranty"
        internal const val FIELD_CURRENCY_ID = "currency_id"
    }

    val thumbnail: String?
        get() = _thumbnail?.replaceFirst(
            oldValue = HTTP,
            newValue = HTTPS,
            ignoreCase = false
        )
    val freeShipping: Boolean?
        get() = _shipping.freeShipping

    override fun getEntityName(): String = TABLE_NAME

    override fun mandatoryFields(): Map<String, Any?> = mapOf(
        Pair(FIELD_ID, id),
        Pair(FIELD_TITLE, title),
        Pair(FIELD_PRICE, price),
        Pair(FIELD_SELLER_ID, sellerId)
    )
}
