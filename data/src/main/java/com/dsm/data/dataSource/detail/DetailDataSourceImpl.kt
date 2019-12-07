package com.dsm.data.dataSource.detail

import com.dsm.data.addSchedulers
import com.dsm.data.local.dao.GifDao
import com.dsm.data.local.entity.GifRoomData
import com.dsm.data.remote.GiphyApi
import com.dsm.data.remote.entity.GifData
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val gifDao: GifDao
) : DetailDataSource {

    override fun getRemoteGifDetail(gifId: String): Flowable<GifData> =
        giphyApi.getGifDetail(gifId).addSchedulers()

    override fun getLocalGifDetail(gifId: String): GifRoomData =
        gifDao.getGifDetail(gifId)

    override fun addLocalGifData(gifRoomData: GifRoomData): Completable {
    }

    override fun setFavorite(gifId: String, isFavorite: Boolean): Completable {
    }
}