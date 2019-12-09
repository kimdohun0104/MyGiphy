package com.dsm.data.dataSource.search

import com.dsm.data.addSchedulers
import com.dsm.data.local.dao.GifDao
import com.dsm.data.local.entity.GifRoomEntity
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import com.dsm.data.remote.GiphyApi
import com.dsm.data.remote.entity.GifListData
import io.reactivex.Completable
import io.reactivex.Flowable

class SearchDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val gifDao: GifDao
) : SearchDataSource {

    override fun searchGifList(page: Int, q: String): Flowable<GifListData> =
        giphyApi.searchGifList(page, q).addSchedulers()

    override fun searchLocalGifList(page: Int, q: String): List<GifRoomEntity>? =
        gifDao.searchGifList(page, q)

    override fun saveLocalGifList(gifRoomEntityList: List<GifRoomEntity>): Completable =
        gifDao.saveGifList(gifRoomEntityList)

    override fun saveSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable =
        gifDao.saveSearchHistory(searchHistoryRoomEntity)
}