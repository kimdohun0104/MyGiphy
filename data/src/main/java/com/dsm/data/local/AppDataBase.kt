package com.dsm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dsm.data.local.dao.GifDao
import com.dsm.data.local.entity.GifRoomEntity

@Database(
    entities = [
        GifRoomEntity::class
    ], version = 2
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gifDao(): GifDao
}