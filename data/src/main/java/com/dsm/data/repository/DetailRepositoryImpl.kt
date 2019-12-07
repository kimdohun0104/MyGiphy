package com.dsm.data.repository

import com.dsm.data.dataSource.detail.DetailDataSource
import com.dsm.data.mapper.GifDetailEntityMapper
import com.dsm.domain.entity.GifDetailEntity
import com.dsm.domain.repository.DetailRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailRepositoryImpl(
    private val dataSource: DetailDataSource,
    private val gifDetailEntityMapper: GifDetailEntityMapper
) : DetailRepository {

    override fun getRemoteGifDetail(gifId: String, isFavorite: Boolean): Flowable<GifDetailEntity> =
        dataSource.getRemoteGifDetail(gifId).map { gifDetailEntityMapper.mapFrom(it.gifData, isFavorite) }

    override fun getLocalGifDetail(gifId: String): GifDetailEntity? =
        gifDetailEntityMapper.mapFrom(dataSource.getLocalGifDetail(gifId))

    override fun saveGifDetail(gifEntity: GifDetailEntity, isFavorite: Boolean): Completable =
        dataSource.saveGifDetail(gifDetailEntityMapper.mapFrom(gifEntity, isFavorite))

    override fun isFavoriteGif(gifId: String): Boolean =
        dataSource.isFavoriteGif(gifId)

    override fun setFavorite(gifId: String, isFavorite: Boolean): Completable =
        dataSource.setFavorite(gifId, isFavorite)

}