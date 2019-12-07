package com.dsm.data.mapper

import com.dsm.data.local.entity.GifRoomEntity
import com.dsm.data.remote.entity.GifData
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.GifEntity

class GifEntityMapper : Mapper<GifData, GifEntity> {
    override fun mapFrom(from: GifData) = GifEntity(
        id = from.id,
        height = from.imageData.fixedWidthImageData.height.toInt(),
        gifUrl = from.imageData.fixedWidthImageData.gifUrl,
        displayName = from.user?.displayName ?: "",
        userName = from.user?.userName ?: "",
        avatarUrl = from.user?.avatarUrl ?: ""
    )

    fun roomToEntity(from: GifRoomEntity) = GifEntity(
        id = from.id,
        avatarUrl = from.avatarUrl,
        userName = from.userName,
        displayName = from.displayName,
        gifUrl = from.gifUrl,
        height = from.height
    )

    fun entityToRoom(from: GifEntity) = GifRoomEntity(
        id = from.id,
        height = from.height,
        gifUrl = from.gifUrl,
        displayName = from.displayName,
        userName = from.userName,
        avatarUrl = from.avatarUrl
    )
}