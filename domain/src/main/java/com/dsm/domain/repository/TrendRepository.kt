package com.dsm.domain.repository

import com.dsm.domain.entity.GifEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface TrendRepository {

    fun getRemoteTrendList(offset: Int): Flowable<List<GifEntity>>

    fun getLocalTrendList(offset: Int): List<GifEntity>?

    fun saveLocalGifList(gifEntityList: List<GifEntity>): Completable
}