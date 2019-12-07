package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ImagesData(
    @SerializedName("fixed_width")
    val fixedWidthImageData: FixedWidthImageData
)