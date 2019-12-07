package com.dsm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsm.data.local.dao.GifDetailDao
import com.dsm.data.local.entity.GifDetailRoomEntity

@Database(
    entities = [
        GifDetailRoomEntity::class
    ], version = 2
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gifDetailDao(): GifDetailDao
}