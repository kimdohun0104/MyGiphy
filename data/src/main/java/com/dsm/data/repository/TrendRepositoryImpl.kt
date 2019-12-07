package com.dsm.data.repository

import com.dsm.data.dataSource.trend.TrendDataSource
import com.dsm.data.mapper.GifEntityMapper
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.repository.TrendRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class TrendRepositoryImpl(
    private val dataSource: TrendDataSource,
    private val gifEntityMapper: GifEntityMapper
) : TrendRepository {

    override fun getRemoteTrendList(page: Int): Flowable<List<GifEntity>> =
        dataSource.getRemoteTrendList(page).map { data -> data.gifList.map { gifEntityMapper.mapFrom(it) } }

    override fun getLocalTrendList(page: Int): List<GifEntity>? =
        dataSource.getLocalTrendList(page)?.map(gifEntityMapper::roomToEntity)

    override fun saveLocalGifList(gifEntityList: List<GifEntity>): Completable =
        dataSource.saveLocalGifList(gifEntityList.map { entity -> gifEntityMapper.entityToRoom(entity) })
}