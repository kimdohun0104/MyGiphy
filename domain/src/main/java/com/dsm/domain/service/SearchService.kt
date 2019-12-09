package com.dsm.domain.service

import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.Resource
import io.reactivex.Flowable

interface SearchService {

    fun searchGifList(page: Int, q: String): Flowable<Resource<List<GifEntity>>>
}