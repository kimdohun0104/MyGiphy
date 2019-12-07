package com.dsm.data.dataSource.trend

import com.dsm.data.addSchedulers
import com.dsm.data.remote.GiphyApi
import com.dsm.data.remote.entity.GifListData
import io.reactivex.Flowable

class TrendDataSourceImpl(private val giphyApi: GiphyApi) : TrendDataSource {

    override fun getRemoteTrendList(page: Int): Flowable<GifListData> =
        giphyApi.getTrendGifs(page).addSchedulers()
}