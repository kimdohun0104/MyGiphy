package com.dsm.mygiphy.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.dsm.data.local.dao.GifDao
import com.dsm.mygiphy.presentation.base.BaseViewModel
import com.dsm.mygiphy.presentation.model.GifModel

class DetailViewModel(
    private val gifDao: GifDao
) : BaseViewModel() {

    val gifModel = MutableLiveData<GifModel>()
    val isFavorite = MutableLiveData<Boolean>()

    fun getIsFavorite() {
        isFavorite.value = gifDao.isFavoriteGif(gifModel.value?.id ?: "")
    }

    fun onClickFavorite() {
        (!isFavorite.value!!).let {
            gifDao.setFavorite(gifModel.value!!.id, it).subscribe()
            isFavorite.value = it
        }
    }

    fun setGifModel(gifModel: GifModel) {
        this.gifModel.value = gifModel
    }
}