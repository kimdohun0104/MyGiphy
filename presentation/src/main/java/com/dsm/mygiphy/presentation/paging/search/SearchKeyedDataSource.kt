package com.dsm.mygiphy.presentation.paging.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.usecase.SearchGifListUseCase
import com.dsm.mygiphy.presentation.paging.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SearchKeyedDataSource(
    private val searchGifListUseCase: SearchGifListUseCase,
    private val composite: CompositeDisposable,
    private val search: String
) : PageKeyedDataSource<Int, GifEntity>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GifEntity>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            searchGifListUseCase.create(SearchGifListUseCase.Params(1, search))
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
            searchGifListUseCase.create(SearchGifListUseCase.Params(params.key, search))
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