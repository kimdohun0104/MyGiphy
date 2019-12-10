package com.dsm.mygiphy.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.dsm.data.local.dao.GifDao
import com.dsm.mygiphy.R
import com.dsm.mygiphy.presentation.base.BaseViewModel
import com.dsm.mygiphy.presentation.base.SingleLiveEvent
import com.dsm.mygiphy.presentation.model.GifModel

class DetailViewModel(
    private val gifDao: GifDao
) : BaseViewModel() {

    val gifModel = MutableLiveData<GifModel>()
    val isFavorite = MutableLiveData<Boolean>()

    val toastEvent = SingleLiveEvent<Int>()

    fun getIsFavorite() {
        isFavorite.value = gifDao.isFavoriteGif(gifModel.value?.id ?: "")
    }

    fun onClickFavorite() {
        (!isFavorite.value!!).let {
            gifDao.setFavorite(gifModel.value!!.id, it).subscribe()
            isFavorite.value = it

            toastEvent.value =
                if (it) R.string.set_favorite
                else R.string.set_unfavorite
        }
    }

    fun setGifModel(gifModel: GifModel) {
        this.gifModel.value = gifModel
    }
}