package com.dsm.mygiphy.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.GetGifDetailUseCase
import com.dsm.domain.usecase.SetFavoriteUseCase
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.mygiphy.R
import com.dsm.mygiphy.presentation.base.BaseViewModel
import com.dsm.mygiphy.presentation.mapper.GifDetailModelMapper
import com.dsm.mygiphy.presentation.model.GifDetailModel

class DetailViewModel(
    private val getGifDetailUseCase: GetGifDetailUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val gifDetailModelMapper: GifDetailModelMapper
) : BaseViewModel() {

    val gifDetail = MutableLiveData<GifDetailModel>()
    val isFavorite = MutableLiveData<Boolean>()

    val snackbarRetryEvent = SingleLiveEvent<Unit>()
    val toastEvent = SingleLiveEvent<Int>()

    fun getGifDetail(gifId: String) {
        addDisposable(
            getGifDetailUseCase.create(gifId)
                .subscribe({
                    when (it) {
                        is Resource.Success -> {
                            if (it.isLocal) snackbarRetryEvent.call()
                            gifDetailModelMapper.mapFrom(it.data).let { detail ->
                                gifDetail.value = detail
                                isFavorite.value = detail.isFavorite
                            }
                        }
                        is Resource.Error -> {
                            toastEvent.value = when (it.error) {
                                is ErrorEntity.NotFound -> R.string.fail_not_found
                                else -> R.string.fail_internal
                            }
                        }
                    }
                }, {})
        )
    }

    fun onClickFavorite(gifId: String) {
        (isFavorite.value ?: false).let {
            setFavoriteUseCase.create(SetFavoriteUseCase.Params(gifId, !it)).subscribe()
            isFavorite.value = !it

            toastEvent.value =
                if (!it) R.string.set_favorite
                else R.string.set_unfavorite
        }
    }
}