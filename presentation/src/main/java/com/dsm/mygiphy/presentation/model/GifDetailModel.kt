package com.dsm.mygiphy.presentation.model

data class GifDetailModel(

    val id: String,

    val gifUrl: String,

    val displayName: String,

    val userName: String,

    val avatarUrl: String,

    val isFavorite: Boolean
)