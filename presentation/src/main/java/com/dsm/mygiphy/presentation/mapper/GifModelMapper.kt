package com.dsm.mygiphy.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.GifEntity
import com.dsm.mygiphy.presentation.model.GifModel

class GifModelMapper : Mapper<GifEntity, GifModel> {
    override fun mapFrom(from: GifEntity) = GifModel(
        id = from.id,
        gifUrl = from.gifUrl,
        height = from.height
    )

}