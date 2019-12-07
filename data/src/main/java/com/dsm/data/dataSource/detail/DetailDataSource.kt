package com.dsm.data.dataSource.detail

import com.dsm.data.local.entity.GifDetailRoomEntity
import com.dsm.data.remote.entity.SingleGifData
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailDataSource {

    fun getRemoteGifDetail(gifId: String): Flowable<SingleGifData>

    fun getLocalGifDetail(gifId: String): GifDetailRoomEntity?

    fun saveGifDetail(gifDao: GifDetailRoomEntity): Completable

    fun isFavoriteGif(gifId: String): Boolean

    fun setFavorite(gifId: String, isFavorite: Boolean): Completable
}