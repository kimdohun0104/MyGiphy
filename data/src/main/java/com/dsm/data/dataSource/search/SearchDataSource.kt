package com.dsm.data.dataSource.search

import com.dsm.data.local.entity.GifRoomEntity
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import com.dsm.data.remote.entity.GifListData
import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchDataSource {

    fun searchRemoteGifList(offset: Int, q: String): Flowable<GifListData>

    fun searchLocalGifList(offset: Int, q: String): List<GifRoomEntity>?

    fun saveLocalGifList(gifRoomEntityList: List<GifRoomEntity>): Completable

    fun saveSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable
}