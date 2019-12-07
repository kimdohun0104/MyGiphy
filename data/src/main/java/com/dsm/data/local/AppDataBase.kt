package com.dsm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsm.data.local.dao.GifDao
import com.dsm.data.local.entity.GifRoomData

@Database(
    entities = [
        GifRoomData::class
    ], version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gifDao(): GifDao
}