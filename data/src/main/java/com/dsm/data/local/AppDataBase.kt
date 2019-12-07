package com.dsm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsm.data.local.dao.GifDetailDao
import com.dsm.data.local.entity.GifDetailRoomData

@Database(
    entities = [
        GifDetailRoomData::class
    ], version = 2
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gifDetailDao(): GifDetailDao
}