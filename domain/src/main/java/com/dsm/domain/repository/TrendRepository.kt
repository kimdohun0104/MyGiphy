package com.dsm.domain.repository

import com.dsm.domain.entity.GifEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface TrendRepository {

    fun getRemoteTrendList(page: Int): Flowable<List<GifEntity>>

    fun getLocalTrendList(page: Int): List<GifEntity>?

    fun saveLocalGifList(gifEntityList: List<GifEntity>): Completable
}