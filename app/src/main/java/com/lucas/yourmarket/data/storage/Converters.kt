package com.lucas.yourmarket.data.storage

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lucas.yourmarket.domain.model.Picture
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun fromString(value: String?): List<Picture> {
        val listType: Type = object : TypeToken<List<Picture>?>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Picture>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}
