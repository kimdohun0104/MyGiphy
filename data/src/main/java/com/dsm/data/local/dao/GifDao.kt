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

    @Query("SELECT * FROM GifRoomData LIMIT :pageSize OFFSET :page")
    fun getTrendList(page: Int, pageSize: Int): List<GifRoomData>?
}