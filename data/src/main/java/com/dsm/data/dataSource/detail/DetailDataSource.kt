package com.dsm.data.dataSource.detail

import com.dsm.data.local.entity.GifDetailRoomData
import com.dsm.data.remote.entity.GifData
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailDataSource {

    fun getRemoteGifDetail(gifId: String): Flowable<GifData>

    fun getLocalGifDetail(gifId: String): GifDetailRoomData?

    fun saveGifDetail(gifDetailRoomData: GifDetailRoomData): Completable

    fun isFavoriteGif(gifId: String): Boolean

    fun setFavorite(gifId: String, isFavorite: Boolean): Completable
}