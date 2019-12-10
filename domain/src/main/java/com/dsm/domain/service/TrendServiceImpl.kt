package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import com.dsm.domain.repository.TrendRepository
import io.reactivex.Flowable

class TrendServiceImpl(
    private val repository: TrendRepository,
    private val errorHandler: ErrorHandler
) : TrendService {

    override fun getTrendList(page: Int): Flowable<Success<List<GifEntity>>> =
        (if (page > 1) 25 * (page - 1) else 0).let { p ->
            repository.getRemoteTrendList(p)
                .doOnNext { repository.saveLocalGifList(it).subscribe() }
                .map { Success(it) }
                .onErrorReturn {
                    repository.getLocalTrendList(p)?.let { list ->
                        return@onErrorReturn Success(list, true)
                    }
                    throw errorHandler.getError(it)
                }
        }
}