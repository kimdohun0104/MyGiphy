package com.dsm.data.dataSource.search

import com.dsm.data.addSchedulers
import com.dsm.data.local.dao.GifDao
import com.dsm.data.local.dao.SearchHistoryDao
import com.dsm.data.local.entity.GifRoomEntity
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import com.dsm.data.remote.GiphyApi
import com.dsm.data.remote.entity.GifListData
import io.reactivex.Completable
import io.reactivex.Flowable

class SearchDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val gifDao: GifDao,
    private val searchHistoryDao: SearchHistoryDao
) : SearchDataSource {

    override fun searchRemoteGifList(offset: Int, q: String): Flowable<GifListData> =
        giphyApi.searchGifList(offset, q).addSchedulers()

    override fun searchLocalGifList(offset: Int, q: String): List<GifRoomEntity>? =
        gifDao.searchGifList(offset, q)

    override fun saveLocalGifList(gifRoomEntityList: List<GifRoomEntity>): Completable =
        gifDao.saveGifList(gifRoomEntityList)

    override fun saveSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable =
        searchHistoryDao.saveSearchHistory(searchHistoryRoomEntity)
}