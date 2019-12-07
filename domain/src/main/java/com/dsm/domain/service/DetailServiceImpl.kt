package com.dsm.domain.service

import com.dsm.domain.entity.GifDetailEntity
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.DetailRepository
import com.dsm.domain.toResource
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailServiceImpl(
    private val repository: DetailRepository,
    private val errorHandler: ErrorHandler
) : DetailService {

    override fun getGifDetail(gifId: String): Flowable<Resource<GifDetailEntity>> =
        repository.getRemoteGifDetail(gifId)
            .doOnNext { repository.saveGifDetail(it, repository.isFavoriteGif(gifId)).subscribe() }
            .toResource(errorHandler, repository.getLocalGifDetail(gifId))

    override fun setFavorite(gifId: String, isFavorite: Boolean): Completable =
        repository.setFavorite(gifId, isFavorite)
}