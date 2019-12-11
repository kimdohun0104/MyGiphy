package com.dsm.mygiphy.presentation.ui.detail

import com.dsm.data.local.dao.GifDao
import com.dsm.mygiphy.presentation.base.BaseViewModel

class DetailViewModel(
    private val gifDao: GifDao
) : BaseViewModel() {

    fun isFavoriteGif(gifId: String) =
        gifDao.isFavoriteGif(gifId)

    fun favoriteGif(gifId: String, currentStatus: Boolean) =
        addDisposable(gifDao.setFavorite(gifId, !currentStatus).subscribe())
}