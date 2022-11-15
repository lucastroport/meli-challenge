package com.lucas.yourmarket.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = State.TABLE_NAME)
data class State(
    @PrimaryKey
    @ColumnInfo(name = FIELD_ID)
    @SerializedName(FIELD_ID)
    val id: String,
    @ColumnInfo(name = FIELD_NAME)
    @SerializedName(FIELD_NAME)
    val name: String?
) : Parcelable, BaseModel() {

    companion object {
        internal const val TABLE_NAME = "state"
        internal const val FIELD_ID = "id"
        internal const val FIELD_NAME = "name"
    }

    override fun getEntityName(): String = TABLE_NAME

    override fun mandatoryFields(): Map<String, Any?> = mapOf(
        Pair(FIELD_ID, id),
        Pair(FIELD_NAME, name)
    )
}
