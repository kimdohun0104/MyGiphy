package com.dsm.domain.repository

import com.dsm.domain.entity.GifEntity
import io.reactivex.Flowable

interface TrendRepository {

    fun getRemoteTrendList(page: Int): Flowable<List<GifEntity>>
}