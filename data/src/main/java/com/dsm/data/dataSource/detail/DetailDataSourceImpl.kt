package com.dsm.data.dataSource.detail

import com.dsm.data.addSchedulers
import com.dsm.data.local.dao.GifDetailDao
import com.dsm.data.remote.GiphyApi
import com.dsm.data.remote.entity.SingleGifData
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val gifDao: GifDetailDao
) : DetailDataSource {

    override fun getRemoteGifDetail(gifId: String): Flowable<SingleGifData> =
        giphyApi.getGifDetail(gifId).addSchedulers()

    override fun getLocalGifDetail(gifId: String): com.dsm.data.local.entity.GifDetailRoomEntity? =
        gifDao.getGifDetail(gifId)

    override fun saveGifDetail(gifDao: com.dsm.data.local.entity.GifDetailRoomEntity): Completable =
        this.gifDao.saveGifDetail(gifDao)

    override fun isFavoriteGif(gifId: String): Boolean =
        gifDao.isFavoriteGif(gifId)

    override fun setFavorite(gifId: String, isFavorite: Boolean): Completable =
        gifDao.setFavorite(gifId, isFavorite)

}