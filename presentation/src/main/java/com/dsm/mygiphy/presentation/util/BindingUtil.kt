package com.dsm.mygiphy.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingUtil {

    @JvmStatic
    @BindingAdapter("gifUrl")
    fun bindGifUrl(view: ImageView, gifUrl: String?) {
        gifUrl?.let {
            GlideApp.with(view)
                .load(it)
                .override(view.width, view.height)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImageUrl(view: ImageView, imageUrl: String?) {
        imageUrl?.let {
            GlideApp.with(view).load(it).into(view)
        }
    }
}