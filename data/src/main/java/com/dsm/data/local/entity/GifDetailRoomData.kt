package com.dsm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GifDetailRoomData(

    @PrimaryKey
    val id: String,

    val gifUrl: String,

    val avatarUrl: String,

    val displayName: String,

    val userName: String,

    val isFavorite: Boolean
)