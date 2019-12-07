package com.dsm.data.remote.entity

import com.google.gson.annotations.SerializedName

data class UserData(

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("display_name")
    val displayName: String
)