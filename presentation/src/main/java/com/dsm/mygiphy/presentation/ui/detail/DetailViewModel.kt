package com.dsm.mygiphy.presentation.ui.detail

import com.dsm.data.local.dao.GifDao
import com.dsm.mygiphy.presentation.base.BaseViewModel

class DetailViewModel(
    private val gifDao: GifDao
) : BaseViewModel() {

    fun isFavoriteGif(gifId: String) =
        gifDao.isFavoriteGifLiveData(gifId)

    fun favoriteGif(gifId: String) {
        (!gifDao.isFavoriteGif(gifId)).let {
            gifDao.setFavorite(gifId, it).subscribe()
        }
    }
}