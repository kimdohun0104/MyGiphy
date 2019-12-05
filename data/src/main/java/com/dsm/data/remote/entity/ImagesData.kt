package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ImagesData(
    @SerializedName("fixed_height")
    val fixedHeightImageData: FixedHeightImageData
)