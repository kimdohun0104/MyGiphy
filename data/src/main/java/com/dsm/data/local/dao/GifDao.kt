package com.dsm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.entity.GifRoomData
import io.reactivex.Completable

@Dao
interface GifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTrendList(gifList: List<GifRoomData>): Completable

    @Query("SELECT * FROM GifRoomData LIMIT 25 OFFSET :page")
    fun getTrendList(page: Int): List<GifRoomData>?
}