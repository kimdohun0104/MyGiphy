package com.dsm.data.mapper

import com.dsm.data.local.entity.GifDetailRoomData
import com.dsm.data.remote.entity.GifData
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.GifDetailEntity

class GifDetailEntityMapper : Mapper<GifData, GifDetailEntity> {
    override fun mapFrom(from: GifData) = GifDetailEntity(
        id = from.id,
        gifUrl = from.imageData.fixedWidthImageData.gifUrl,
        displayName = from.user.displayName,
        userName = from.user.userName,
        avatarUrl = from.user.avatarUrl
    )

    fun mapFrom(from: GifDetailRoomData?) = from?.let {
        GifDetailEntity(
            id = from.id,
            gifUrl = from.gifUrl,
            userName = from.userName,
            displayName = from.displayName,
            avatarUrl = from.avatarUrl
        )
    }

    fun mapFrom(from: GifDetailEntity, isFavorite: Boolean) = GifDetailRoomData(
        id = from.id,
        displayName = from.displayName,
        userName = from.userName,
        gifUrl = from.gifUrl,
        avatarUrl = from.avatarUrl,
        isFavorite = isFavorite
    )
}