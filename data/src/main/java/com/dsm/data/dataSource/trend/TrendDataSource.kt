package com.dsm.data.dataSource.trend

import com.dsm.data.local.entity.GifRoomEntity
import com.dsm.data.remote.entity.GifListData
import io.reactivex.Completable
import io.reactivex.Flowable

interface TrendDataSource {

    fun getRemoteTrendList(page: Int): Flowable<GifListData>

    fun getLocalTrendList(page: Int): List<GifRoomEntity>?

    fun saveLocalGifList(gifRoomEntityList: List<GifRoomEntity>): Completable
}