package com.dsm.data.local

import androidx.room.TypeConverter
import com.dsm.data.local.entity.GifRoomEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    @TypeConverter
    @JvmStatic
    fun GifListToString(list: List<GifRoomEntity>) = Gson().toJson(list)

    @TypeConverter
    @JvmStatic
    fun stringToGifList(value: String): List<GifRoomEntity> {
        val listType = object: TypeToken<List<GifRoomEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}