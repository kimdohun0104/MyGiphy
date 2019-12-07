package com.dsm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.entity.GifDetailRoomEntity
import io.reactivex.Completable

@Dao
interface GifDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGifDetail(gifDao: GifDetailRoomEntity): Completable

    @Query("SELECT * FROM GifDetailRoomEntity WHERE id = :gifId")
    fun getGifDetail(gifId: String): GifDetailRoomEntity

    @Query("UPDATE GifDetailRoomEntity SET isFavorite = :isFavorite WHERE id = :gifId")
    fun setFavorite(gifId: String, isFavorite: Boolean): Completable

    @Query("SELECT isFavorite FROM GifDetailRoomEntity WHERE id = :gifId")
    fun isFavoriteGif(gifId: String): Boolean
}