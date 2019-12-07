package com.dsm.data.mapper

import com.dsm.data.local.entity.GifDetailRoomEntity
import com.dsm.data.remote.entity.GifData
import com.dsm.domain.entity.GifDetailEntity

class GifDetailEntityMapper {
    fun mapFrom(from: GifData, isFavorite: Boolean) = GifDetailEntity(
        id = from.id,
        gifUrl = from.imageData.fixedWidthImageData.gifUrl,
        displayName = from.user?.displayName ?: "",
        userName = from.user?.userName ?: "",
        avatarUrl = from.user?.avatarUrl ?: "",
        isFavorite = isFavorite
    )

    fun mapFrom(from: GifDetailRoomEntity?) = from?.let {
        GifDetailEntity(
            id = from.id,
            gifUrl = from.gifUrl,
            userName = from.userName,
            displayName = from.displayName,
            avatarUrl = from.avatarUrl,
            isFavorite = from.isFavorite
        )
    }

    fun mapFrom(from: GifDetailEntity, isFavorite: Boolean) = GifDetailRoomEntity(
        id = from.id,
        displayName = from.displayName,
        userName = from.userName,
        gifUrl = from.gifUrl,
        avatarUrl = from.avatarUrl,
        isFavorite = isFavorite
    )
}