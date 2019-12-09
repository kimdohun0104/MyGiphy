package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Flowable

class SearchServiceImpl(
    private val repository: SearchRepository,
    private val errorHandler: ErrorHandler
) : SearchService {

    override fun searchGifList(page: Int, q: String): Flowable<Resource<List<GifEntity>>> =
        repository.searchGifList(page, q)
            .doOnNext {
                repository.saveLocalGifList(it).subscribe()
                repository.saveSearchHistory(q).subscribe()
            }
            .map<Resource<List<GifEntity>>> { Resource.Success(it) }
            .onErrorReturn {
                repository.searchLocalGifList(page, q)?.let { gifList ->
                    return@onErrorReturn Resource.Success(gifList, true)
                }
                Resource.Error(errorHandler.getError(it))
            }

}