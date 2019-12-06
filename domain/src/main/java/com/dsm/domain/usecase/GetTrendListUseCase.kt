package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.service.TrendService
import io.reactivex.Flowable

class GetTrendListUseCase(
    private val trendService: TrendService
) : UseCase<Int, Resource<List<GifEntity>>>() {

    override fun create(data: Int): Flowable<Resource<List<GifEntity>>> =
        trendService.getTrendList(data)

}