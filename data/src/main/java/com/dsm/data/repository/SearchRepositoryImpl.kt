package com.dsm.data.repository

import com.dsm.data.dataSource.search.SearchDataSource
import com.dsm.data.local.entity.SearchHistoryRoomEntity
import com.dsm.data.mapper.GifEntityMapper
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.repository.SearchRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class SearchRepositoryImpl(
    private val dataSource: SearchDataSource,
    private val gifEntityMapper: GifEntityMapper
) : SearchRepository {

    override fun searchGifList(offset: Int, q: String): Flowable<List<GifEntity>> =
        dataSource.searchGifList(offset, q).map { data ->  data.gifList.map { gifEntityMapper.mapFrom(it) } }

    override fun searchLocalGifList(offset: Int, q: String): List<GifEntity>? =
        dataSource.searchLocalGifList(offset, q)?.map(gifEntityMapper::roomToEntity)

    override fun saveLocalGifList(gifEntityList: List<GifEntity>): Completable =
        dataSource.saveLocalGifList(gifEntityList.map(gifEntityMapper::entityToRoom))

    override fun saveSearchHistory(search: String): Completable =
        dataSource.saveSearchHistory(SearchHistoryRoomEntity(search))
}