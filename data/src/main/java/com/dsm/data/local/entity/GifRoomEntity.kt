package com.dsm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GifRoomEntity(

    @PrimaryKey
    val id: String,

    val gifUrl: String,

    val avatarUrl: String,

    val displayName: String,

    val userName: String,

    val height: Int
)