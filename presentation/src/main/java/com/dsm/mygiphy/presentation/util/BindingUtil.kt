package com.dsm.mygiphy.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dsm.mygiphy.R

object BindingUtil {

    @JvmStatic
    @BindingAdapter("gifUrl")
    fun bindGifUrl(view: ImageView, gifUrl: String?) {
        gifUrl?.let {
            Glide.with(view).asGif()
                .load(it)
                .placeholder(R.mipmap.ic_launcher)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImageUrl(view: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(view)
                .load(it)
                .placeholder(R.mipmap.ic_launcher)
                .into(view)
        }
    }
}