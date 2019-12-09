package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Flowable

class SearchServiceImpl(
    private val repository: SearchRepository,
    private val errorHandler: ErrorHandler
) : SearchService {

    override fun searchGifList(page: Int, q: String): Flowable<Success<List<GifEntity>>> =
        repository.searchGifList(page, q)
            .doOnNext {
                repository.saveLocalGifList(it).subscribe()
                repository.saveSearchHistory(q).subscribe()
            }
            .map { Success(it) }
            .onErrorReturn {
                repository.searchLocalGifList(page, q)?.let { gifList ->
                    return@onErrorReturn Success(gifList, true)
                }
                throw errorHandler.getError(it)
            }

}