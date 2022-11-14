package com.lucas.yourmarket.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = User.TABLE_NAME)
data class User(
    @PrimaryKey
    @ColumnInfo(name = FIELD_ID)
    @SerializedName(FIELD_ID)
    val id: Long,
    @ColumnInfo(name = FIELD_NICKNAME)
    @SerializedName(FIELD_NICKNAME)
    val nickname: String?,
    @Embedded
    @SerializedName(FIELD_ADDRESS)
    val address: Address,
    @Embedded
    @SerializedName(FIELD_SELLER_REPUTATION)
    val sellerReputation: SellerReputation

) : Parcelable, BaseModel() {

    companion object {
        internal const val TABLE_NAME = "user"
        internal const val FIELD_ID = "id"
        internal const val FIELD_NICKNAME = "nickname"
        internal const val FIELD_ADDRESS = "address"
        internal const val FIELD_SELLER_REPUTATION = "seller_reputation"
    }

    override fun getEntityName(): String = TABLE_NAME

    override fun mandatoryFields(): Map<String, Any?> = mapOf(
        Pair(FIELD_ID, id),
        Pair(FIELD_NICKNAME, nickname)
    )

}
