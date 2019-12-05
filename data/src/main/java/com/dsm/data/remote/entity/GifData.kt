package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class GifData(

    val id: String,

    @SerializedName("images")
    val imageData: ImagesData
)