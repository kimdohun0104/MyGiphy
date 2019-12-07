package com.dsm.mygiphy.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.GifDetailEntity
import com.dsm.mygiphy.presentation.model.GifDetailModel

class GifDetailModelMapper : Mapper<GifDetailEntity, GifDetailModel> {
    override fun mapFrom(from: GifDetailEntity) = GifDetailModel(
        id = from.id,
        displayName = from.displayName,
        userName = from.userName,
        avatarUrl = from.avatarUrl,
        gifUrl = from.gifUrl,
        isFavorite = from.isFavorite
    )


}