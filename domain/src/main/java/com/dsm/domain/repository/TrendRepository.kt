package com.dsm.domain.repository

import com.dsm.domain.entity.GifEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface TrendRepository {

    fun getRemoteTrendList(page: Int, pageSize: Int): Flowable<List<GifEntity>>

    fun getLocalTrendList(page: Int, pageSize: Int): List<GifEntity>?

    fun saveTrendList(gifList: List<GifEntity>): Completable
}