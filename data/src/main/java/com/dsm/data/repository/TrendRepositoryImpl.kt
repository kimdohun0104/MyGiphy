package com.dsm.data.repository

import com.dsm.data.dataSource.TrendDataSource
import com.dsm.data.mapper.GifDataMapper
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.repository.TrendRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class TrendRepositoryImpl(
    private val dataSource: TrendDataSource,
    private val gifDataMapper: GifDataMapper
) : TrendRepository {

    override fun getRemoteTrendList(page: Int): Flowable<List<GifEntity>> =
        dataSource.getRemoteTrendList(page).map { data -> data.gifList.map { gifDataMapper.mapFrom(it) } }

    override fun getLocalTrendList(page: Int): List<GifEntity>? =
        dataSource.getLocalTrendList(page)?.map { gifDataMapper.roomDataToEntity(it) }

    override fun saveTrendList(gifList: List<GifEntity>): Completable =
        dataSource.saveTrendList(gifList.map {  gifDataMapper.entityToRoomData(it) })

}