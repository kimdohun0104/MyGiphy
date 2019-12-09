package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.Success
import io.reactivex.Flowable

interface SearchService {

    fun searchGifList(page: Int, q: String): Flowable<Success<List<GifEntity>>>
}