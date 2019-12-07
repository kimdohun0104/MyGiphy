package com.dsm.domain.service

import com.dsm.domain.entity.GifDetailEntity
import com.dsm.domain.error.Resource
import io.reactivex.Completable
import io.reactivex.Flowable

interface DetailService {

    fun getGifDetail(gifId: String): Flowable<Resource<GifDetailEntity>>

    fun setFavorite(gifId: String, isFavorite: Boolean): Completable
}