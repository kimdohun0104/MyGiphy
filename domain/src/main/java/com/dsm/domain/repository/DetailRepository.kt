package com.dsm.domain.repository

import com.dsm.domain.entity.GifDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailRepository {

    fun getRemoteGifDetail(gifId: String, isFavorite: Boolean): Flowable<GifDetailEntity>

    fun getLocalGifDetail(gifId: String): GifDetailEntity?

    fun saveGifDetail(gifEntity: GifDetailEntity, isFavorite: Boolean): Completable

    fun isFavoriteGif(gifId: String): Boolean

    fun setFavorite(gifId: String, isFavorite: Boolean): Completable
}