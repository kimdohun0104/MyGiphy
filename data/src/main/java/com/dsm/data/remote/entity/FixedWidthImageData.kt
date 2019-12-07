package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class FixedWidthImageData(

    @SerializedName("url")
    val gifUrl: String,

    val width: String,

    val height: String
)