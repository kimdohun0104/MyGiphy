package com.dsm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.entity.GifRoomEntity
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GifDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveGifList(gifRoomEntityList: List<GifRoomEntity>): Completable

    @Query("SELECT * FROM GifRoomEntity LIMIT 25 OFFSET :page")
    fun getGifList(page: Int): List<GifRoomEntity>?

    @Query("SELECT isFavorite FROM GifRoomEntity WHERE id = :gifId")
    fun isFavoriteGif(gifId: String): Boolean

    @Query("UPDATE GifRoomEntity SET isFavorite = :isFavorite WHERE id = :gifId")
    fun setFavorite(gifId: String, isFavorite: Boolean): Completable

    @Query("SELECT * FROM GifRoomEntity WHERE isFavorite = 1")
    fun getFavoriteGifList(): Flowable<List<GifRoomEntity>>

    @Query("SELECT * FROM GifRoomEntity WHERE slug LIKE '%' || :q || '%' LIMIT 25 OFFSET :page")
    fun searchGifList(page: Int, q: String): List<GifRoomEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable

    @Query("SELECT * FROM SearchHistoryRoomEntity")
    fun getSearchHistoryList(): List<SearchHistoryRoomEntity>
}