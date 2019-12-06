package com.dsm.data.dataSource

import com.dsm.data.local.entity.GifRoomData
import com.dsm.data.remote.entity.GifListData
import io.reactivex.Completable
import io.reactivex.Flowable

interface TrendDataSource {

    fun getRemoteTrendList(page: Int): Flowable<GifListData>

    fun getLocalTrendList(page: Int): List<GifRoomData>?

    fun saveTrendList(gifRoomDataList: List<GifRoomData>): Completable
}