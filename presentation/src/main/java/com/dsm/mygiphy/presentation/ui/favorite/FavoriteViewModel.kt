package com.dsm.mygiphy.presentation.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dsm.data.local.dao.GifDao
import com.dsm.mygiphy.presentation.base.BaseViewModel
import com.dsm.mygiphy.presentation.mapper.GifModelMapper
import com.dsm.mygiphy.presentation.model.GifModel

class FavoriteViewModel(
    private val gifDao: GifDao,
    private val gifModelMapper: GifModelMapper
) : BaseViewModel() {

    fun getFavoriteGifList(): LiveData<List<GifModel>> =
        Transformations.map(gifDao.getFavoriteGifList()) { list -> list.map { gifModelMapper.roomToModel(it) } }
}