package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class SingleGifData(

    @SerializedName("data")
    val gifData: GifData
)