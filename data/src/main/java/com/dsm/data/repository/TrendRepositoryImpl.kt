package com.dsm.data.repository

import com.dsm.data.dataSource.trend.TrendDataSource
import com.dsm.data.mapper.GifEntityMapper
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.repository.TrendRepository
import io.reactivex.Flowable

class TrendRepositoryImpl(
    private val dataSource: TrendDataSource,
    private val gifEntityMapper: GifEntityMapper
) : TrendRepository {

    override fun getRemoteTrendList(page: Int): Flowable<List<GifEntity>> =
        dataSource.getRemoteTrendList(page).map { data -> data.gifList.map { gifEntityMapper.mapFrom(it) } }
}