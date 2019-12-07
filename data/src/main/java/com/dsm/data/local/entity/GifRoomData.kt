package com.dsm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GifRoomData(

    @PrimaryKey
    val id: String,

    val gifUrl: String,

    val width: Int,

    val height: Int,

    val avatarUrl: String,

    val displayName: String,

    val isFavorite: Boolean
)