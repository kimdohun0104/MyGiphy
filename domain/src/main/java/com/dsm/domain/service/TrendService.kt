package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.Resource
import io.reactivex.Flowable

interface TrendService {
    fun getTrendList(page: Int, pageSize: Int): Flowable<Resource<List<GifEntity>>>
}