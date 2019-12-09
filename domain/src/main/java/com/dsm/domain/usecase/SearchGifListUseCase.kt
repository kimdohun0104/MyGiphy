package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.Success
import com.dsm.domain.service.SearchService
import io.reactivex.Flowable

class SearchGifListUseCase(
    private val searchService: SearchService
) : UseCase<SearchGifListUseCase.Params, Success<List<GifEntity>>>() {

    override fun create(data: Params): Flowable<Success<List<GifEntity>>> =
        searchService.searchGifList(data.page, data.q)

    data class Params(val page: Int, val q: String)
}