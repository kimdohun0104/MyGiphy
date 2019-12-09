package com.dsm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistoryRoomEntity(

    @PrimaryKey
    val search: String
)