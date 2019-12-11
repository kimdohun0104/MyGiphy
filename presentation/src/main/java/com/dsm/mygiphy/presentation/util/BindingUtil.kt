package com.dsm.mygiphy.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dsm.mygiphy.R
import java.util.*

object BindingUtil {

    private val holderColors = listOf(
        R.color.colorPurple,
        R.color.colorBlue,
        R.color.colorOrange,
        R.color.colorGreen,
        R.color.colorRed
    )

    private val random = Random()

    @JvmStatic
    @BindingAdapter("gifUrl")
    fun bindGifUrl(view: ImageView, gifUrl: String?) {
        gifUrl?.let {
            GlideApp.with(view)
                .load(it)
                .placeholder(holderColors[random.nextInt(5)])
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImageUrl(view: ImageView, imageUrl: String?) {
        imageUrl?.let {
            GlideApp.with(view)
                .load(it)
                .placeholder(R.drawable.ic_person_24dp)
                .into(view)
        }
    }
}