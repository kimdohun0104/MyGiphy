package com.dsm.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.entity.GifRoomEntity
import io.reactivex.Completable

@Dao
interface GifDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveGifList(gifRoomEntityList: List<GifRoomEntity>): Completable

    @Query("SELECT * FROM GifRoomEntity LIMIT 25 OFFSET :offset")
    fun getGifList(offset: Int): List<GifRoomEntity>?

    @Query("SELECT isFavorite FROM GifRoomEntity WHERE id = :gifId")
    fun isFavoriteGif(gifId: String): LiveData<Boolean>

    @Query("UPDATE GifRoomEntity SET isFavorite = :isFavorite WHERE id = :gifId")
    fun setFavorite(gifId: String, isFavorite: Boolean): Completable

    @Query("SELECT * FROM GifRoomEntity WHERE isFavorite = 1")
    fun getFavoriteGifList(): LiveData<List<GifRoomEntity>>

    @Query("SELECT * FROM GifRoomEntity WHERE slug LIKE '%' || :q || '%' LIMIT 25 OFFSET :offset")
    fun searchGifList(offset: Int, q: String): List<GifRoomEntity>?
}