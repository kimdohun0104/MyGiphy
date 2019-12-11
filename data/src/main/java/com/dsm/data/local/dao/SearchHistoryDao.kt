package com.dsm.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import io.reactivex.Completable

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable

    @Query("SELECT * FROM SearchHistoryRoomEntity LIMIT 6")
    fun getSearchHistoryList(): LiveData<List<SearchHistoryRoomEntity>>

    @Query("DELETE FROM SearchHistoryRoomEntity WHERE search = :search")
    fun deleteSearchHistory(search: String): Completable
}