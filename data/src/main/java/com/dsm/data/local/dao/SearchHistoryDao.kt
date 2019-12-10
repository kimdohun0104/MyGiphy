package com.dsm.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import io.reactivex.Completable

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable

    @Query("SELECT * FROM SearchHistoryRoomEntity LIMIT 6")
    fun getSearchHistoryList(): LiveData<List<SearchHistoryRoomEntity>>

    @Delete
    fun deleteSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable
}