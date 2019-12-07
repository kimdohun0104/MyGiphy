package com.dsm.data.mapper

import com.dsm.data.remote.entity.GifData
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.GifEntity

class GifEntityMapper : Mapper<GifData, GifEntity> {
    override fun mapFrom(from: GifData) = GifEntity(
        id = from.id,
        height = from.imageData.fixedWidthImageData.height.toInt(),
        gifUrl = from.imageData.fixedWidthImageData.gifUrl
    )

}