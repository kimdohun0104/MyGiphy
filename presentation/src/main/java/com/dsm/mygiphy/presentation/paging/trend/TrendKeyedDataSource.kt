package com.dsm.mygiphy.presentation.paging.trend

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.error.Resource
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
            getTrendListUseCase.create(0)
                .subscribe({
                    when (it) {
                        is Resource.Success -> {
                            callback.onResult(it.data, null, 1)
                            if (it.isLocal) networkState.postValue(NetworkState.LOCAL)
                            else networkState.postValue(NetworkState.LOADED)
                        }
                        is Resource.Error -> networkState.postValue(NetworkState.FAILED)
                    }
                }, {})
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GifEntity>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            getTrendListUseCase.create(params.key)
                .subscribe({
                    when (it) {
                        is Resource.Success -> {
                            callback.onResult(it.data, params.key + 1)
                            if (it.isLocal) networkState.postValue(NetworkState.LOCAL)
                            else networkState.postValue(NetworkState.LOADED)
                        }
                        is Resource.Error -> networkState.postValue(NetworkState.FAILED)
                    }
                }, {})
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GifEntity>) {
    }
}