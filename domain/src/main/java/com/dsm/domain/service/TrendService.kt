package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.Success
import io.reactivex.Flowable

interface TrendService {
    fun getTrendList(page: Int): Flowable<Success<List<GifEntity>>>
}