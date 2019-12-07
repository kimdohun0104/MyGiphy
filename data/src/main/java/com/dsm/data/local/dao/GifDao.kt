package com.dsm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.entity.GifRoomEntity
import io.reactivex.Completable

@Dao
interface GifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGifList(gifRoomEntityList: List<GifRoomEntity>): Completable

    @Query("SELECT * FROM GifRoomEntity LIMIT 25 OFFSET :page")
    fun getGifList(page: Int): List<GifRoomEntity>?
}