package com.dsm.mygiphy.presentation.paging.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.usecase.SearchGifListUseCase
import io.reactivex.disposables.CompositeDisposable

class SearchDataFactory(
    private val searchGifListUseCase: SearchGifListUseCase,
    private val composite: CompositeDisposable,
    private val search: String
) : DataSource.Factory<Int, GifEntity>() {

    val sourceLiveData = MutableLiveData<SearchKeyedDataSource>()

    override fun create(): DataSource<Int, GifEntity> {
        SearchKeyedDataSource(searchGifListUseCase, composite, search).let {
            sourceLiveData.postValue(it)
            return it
        }
    }
}