package com.dsm.mygiphy.presentation.util

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.dsm.mygiphy.R

@GlideModule
class AppGlide : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(
            RequestOptions().apply {
                placeholder(R.mipmap.ic_launcher)
            }
        )
    }
}