package com.dsm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.entity.GifDetailRoomData
import io.reactivex.Completable

@Dao
interface GifDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGifDetail(gifDetailRoomData: GifDetailRoomData): Completable

    @Query("SELECT * FROM GifDetailRoomData WHERE id = :gifId")
    fun getGifDetail(gifId: String): GifDetailRoomData

    @Query("UPDATE GifDetailRoomData SET isFavorite = :isFavorite WHERE id = :gifId")
    fun setFavorite(gifId: String, isFavorite: Boolean): Completable

    @Query("SELECT isFavorite FROM GifDetailRoomData WHERE id = :gifId")
    fun isFavoriteGif(gifId: String): Boolean
}