package com.dsm.data.dataSource.trend

import com.dsm.data.remote.entity.GifListData
import io.reactivex.Flowable

interface TrendDataSource {

    fun getRemoteTrendList(page: Int): Flowable<GifListData>
}