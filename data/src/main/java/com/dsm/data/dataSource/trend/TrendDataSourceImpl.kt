package com.dsm.data.dataSource.trend

import com.dsm.data.addSchedulers
import com.dsm.data.local.dao.GifDao
import com.dsm.data.local.entity.GifRoomEntity
import com.dsm.data.remote.GiphyApi
import com.dsm.data.remote.entity.GifListData
import io.reactivex.Completable
import io.reactivex.Flowable

class TrendDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val gifDao: GifDao
) : TrendDataSource {

    override fun getRemoteTrendList(page: Int): Flowable<GifListData> =
        giphyApi.getTrendGifList(page).addSchedulers()

    override fun getLocalTrendList(page: Int): List<GifRoomEntity>? =
        gifDao.getGifList(page)

    override fun saveLocalGifList(gifRoomEntityList: List<GifRoomEntity>): Completable =
        gifDao.saveGifList(gifRoomEntityList)
}