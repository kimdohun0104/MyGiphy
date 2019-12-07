package com.dsm.domain.usecase

import com.dsm.domain.base.CompletableUseCase
import com.dsm.domain.service.DetailService
import io.reactivex.Completable

class SetFavoriteUseCase(
    private val detailService: DetailService
) : CompletableUseCase<SetFavoriteUseCase.Params>() {

    data class Params(val gifId: String, val isFavorite: Boolean)

    override fun create(data: Params): Completable =
        detailService.setFavorite(data.gifId, data.isFavorite)
}