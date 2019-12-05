package com.dsm.data.local

import androidx.room.TypeConverter
import com.dsm.data.local.entity.GifRoomData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromGifListToString(list: List<GifRoomData>) = Gson().toJson(list)

    @TypeConverter
    @JvmStatic
    fun fromStringToGifList(value: String): List<GifRoomData> {
        val listType = object: TypeToken<List<GifRoomData>>() {}.type
        return Gson().fromJson(value, listType)
    }
}