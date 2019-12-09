package com.dsm.domain.repository

import com.dsm.domain.entity.GifEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface SearchRepository {

    fun searchGifList(page: Int, q: String): Flowable<List<GifEntity>>

    fun searchLocalGifList(page: Int, q: String): List<GifEntity>?

    fun saveLocalGifList(gifEntityList: List<GifEntity>): Completable

    fun saveSearchHistory(search: String): Completable
}