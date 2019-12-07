package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.GifDetailEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.service.DetailService
import io.reactivex.Flowable

class GetGifDetailUseCase(
    private val detailService: DetailService
) : UseCase<String, Resource<GifDetailEntity>>() {

    override fun create(data: String): Flowable<Resource<GifDetailEntity>> =
        detailService.getGifDetail(data)

}