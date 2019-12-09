package com.dsm.mygiphy.presentation.mapper

import com.dsm.data.local.entity.GifRoomEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.GifEntity
import com.dsm.mygiphy.presentation.model.GifModel

class GifModelMapper : Mapper<GifEntity, GifModel> {
    override fun mapFrom(from: GifEntity) = GifModel(
        id = from.id,
        gifUrl = from.gifUrl,
        height = from.height,
        avatarUrl = from.avatarUrl,
        userName = from.userName,
        displayName = from.displayName
    )

    fun roomToModel(from: GifRoomEntity) = GifModel(
        id = from.id,
        displayName = from.displayName,
        userName = from.userName,
        avatarUrl = from.avatarUrl,
        gifUrl = from.gifUrl,
        height = from.height
    )
}