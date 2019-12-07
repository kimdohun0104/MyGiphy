package com.dsm.data.dataSource.detail

import com.dsm.data.addSchedulers
import com.dsm.data.local.dao.GifDetailDao
import com.dsm.data.local.entity.GifDetailRoomData
import com.dsm.data.remote.GiphyApi
import com.dsm.data.remote.entity.GifData
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val gifDetailDao: GifDetailDao
) : DetailDataSource {

    override fun getRemoteGifDetail(gifId: String): Flowable<GifData> =
        giphyApi.getGifDetail(gifId).addSchedulers()

    override fun getLocalGifDetail(gifId: String): GifDetailRoomData? =
        gifDetailDao.getGifDetail(gifId)

    override fun saveGifDetail(gifDetailRoomData: GifDetailRoomData): Completable =
        gifDetailDao.saveGifDetail(gifDetailRoomData)

    override fun isFavoriteGif(gifId: String): Boolean =
        gifDetailDao.isFavoriteGif(gifId)

    override fun setFavorite(gifId: String, isFavorite: Boolean): Completable =
        gifDetailDao.setFavorite(gifId, isFavorite)

}