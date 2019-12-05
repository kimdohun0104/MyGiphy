package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.TrendRepository
import com.dsm.domain.toResource
import io.reactivex.Flowable

class TrendServiceImpl(
    private val repository: TrendRepository,
    private val errorHandler: ErrorHandler
) : TrendService {

    override fun getTrendList(page: Int, pageSize: Int): Flowable<Resource<List<GifEntity>>> =
        repository.getRemoteTrendList(page, pageSize)
            .doOnNext { repository.saveTrendList(it).subscribe() }
            .toResource(errorHandler, repository.getLocalTrendList(page, pageSize))

}