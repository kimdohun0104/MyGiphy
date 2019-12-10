package com.dsm.mygiphy.presentation.paging.trend

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.usecase.GetTrendListUseCase
import com.dsm.mygiphy.presentation.paging.NetworkState
import io.reactivex.disposables.CompositeDisposable

class TrendKeyedDataSource(
    private val getTrendListUseCase: GetTrendListUseCase
) : PageKeyedDataSource<Int, GifEntity>() {

    val networkState = MutableLiveData<NetworkState>()

    private val composite = CompositeDisposable()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GifEntity>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            getTrendListUseCase.create(1)
                .subscribe({
                    callback.onResult(it.data, null, 2)
                    if (it.isLocal) networkState.postValue(NetworkState.LOCAL)
                    else networkState.postValue(NetworkState.LOADED)
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GifEntity>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            getTrendListUseCase.create(params.key)
                .subscribe({
                    callback.onResult(it.data, params.key + 1)
                    if (it.isLocal) networkState.postValue(NetworkState.LOCAL)
                    else networkState.postValue(NetworkState.LOADED)
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GifEntity>) {
    }
}