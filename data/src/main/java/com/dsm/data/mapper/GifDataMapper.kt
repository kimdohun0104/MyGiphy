package com.dsm.data.mapper

import com.dsm.data.remote.entity.GifData
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.GifEntity

class GifDataMapper: Mapper<GifData, GifEntity> {
    override fun mapFrom(from: GifData) = GifEntity(
        id = from.id,
        gifUrl = from.imageData.fixedWidthImageData.gifUrl,
        width = from.imageData.fixedWidthImageData.width.toInt(),
        height = from.imageData.fixedWidthImageData.height.toInt()
    )
}