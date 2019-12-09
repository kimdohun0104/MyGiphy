package com.dsm.mygiphy.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GifModel(

    val id: String,

    val slug: String,

    val gifUrl: String,

    val height: Int,

    val avatarUrl: String,

    val displayName: String,

    val userName: String
) : Parcelable