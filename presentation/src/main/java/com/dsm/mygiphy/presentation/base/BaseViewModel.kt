package com.dsm.mygiphy.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    val composite = CompositeDisposable()

    fun addDisposable(disposable: Disposable) =
        composite.add(disposable)

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }
}