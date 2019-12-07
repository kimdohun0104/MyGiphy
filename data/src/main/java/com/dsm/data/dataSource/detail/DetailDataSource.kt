package com.dsm.data.dataSource.detail

import com.dsm.data.local.entity.GifRoomData
import com.dsm.data.remote.entity.GifData
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailDataSource {

    fun getRemoteGifDetail(gifId: String): Flowable<GifData>

    fun getLocalGifDetail(gifId: String): GifRoomData

    fun addLocalGifData(gifRoomData: GifRoomData): Completable

    fun setFavorite(gifId: String, isFavorite: Boolean): Completable
}