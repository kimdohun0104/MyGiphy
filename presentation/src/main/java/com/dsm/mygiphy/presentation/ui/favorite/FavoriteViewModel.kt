package com.dsm.mygiphy.presentation.ui.favorite

import androidx.lifecycle.MutableLiveData
import com.dsm.data.local.dao.GifDao
import com.dsm.mygiphy.presentation.base.BaseViewModel
import com.dsm.mygiphy.presentation.mapper.GifModelMapper
import com.dsm.mygiphy.presentation.model.GifModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(
    private val gifDao: GifDao,
    private val gifModelMapper: GifModelMapper
) : BaseViewModel() {

    val favoriteGifList = MutableLiveData<List<GifModel>>()

    fun getFavoriteGifList() {
        addDisposable(
            gifDao.getFavoriteGifList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { list -> list.map { gifModelMapper.roomToModel(it) } }
                .subscribe({
                    favoriteGifList.value = it
                }, {})
        )
    }
}