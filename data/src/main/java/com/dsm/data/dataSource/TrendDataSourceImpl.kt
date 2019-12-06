package com.dsm.data.dataSource

import com.dsm.data.addSchedulers
import com.dsm.data.local.dao.GifDao
import com.dsm.data.local.entity.GifRoomData
import com.dsm.data.remote.GiphyApi
import com.dsm.data.remote.entity.GifListData
import io.reactivex.Completable
import io.reactivex.Flowable

class TrendDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val gifDao: GifDao
) : TrendDataSource {

    override fun getRemoteTrendList(page: Int): Flowable<GifListData> =
        giphyApi.getTrendGifs(page).addSchedulers()

    override fun getLocalTrendList(page: Int): List<GifRoomData>? =
        gifDao.getTrendList(page)

    override fun saveTrendList(gifRoomDataList: List<GifRoomData>): Completable =
        gifDao.saveTrendList(gifRoomDataList)

}