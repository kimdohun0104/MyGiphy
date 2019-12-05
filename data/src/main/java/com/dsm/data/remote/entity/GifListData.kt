package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class GifListData(

    @SerializedName("data")
    val gifList: List<GifData>
)