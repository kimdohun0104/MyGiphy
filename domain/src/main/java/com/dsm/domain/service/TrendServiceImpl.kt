package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.TrendRepository
import io.reactivex.Flowable

class TrendServiceImpl(
    private val repository: TrendRepository,
    private val errorHandler: ErrorHandler
) : TrendService {

    override fun getTrendList(page: Int): Flowable<Resource<List<GifEntity>>> =
        repository.getRemoteTrendList(page)
            .doOnNext { repository.saveLocalGifList(it).subscribe() }
            .map<Resource<List<GifEntity>>> { Resource.Success(it) }
            .onErrorReturn {
                repository.getLocalTrendList(page)?.let { list ->
                    return@onErrorReturn Resource.Success(list, true)
                }
                Resource.Error(errorHandler.getError(it))
            }
}