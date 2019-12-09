package com.dsm.mygiphy.presentation.ui.search.searchResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.domain.usecase.SearchGifListUseCase
import com.dsm.mygiphy.presentation.base.BaseViewModel
import com.dsm.mygiphy.presentation.mapper.GifModelMapper
import com.dsm.mygiphy.presentation.model.GifModel
import com.dsm.mygiphy.presentation.paging.NetworkState
import com.dsm.mygiphy.presentation.paging.search.SearchDataFactory

class SearchResultViewModel(
    searchGifListUseCase: SearchGifListUseCase,
    gifModelMapper: GifModelMapper,
    search: String
) : BaseViewModel() {

    val networkState: LiveData<NetworkState>
    val resultPagedList: LiveData<PagedList<GifModel>>

    val searchLiveData = MutableLiveData<String>()

    init {
        searchLiveData.value = search

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(25)
            .build()

        val dataFactory = SearchDataFactory(searchGifListUseCase, search)
        val modelDataFactory = dataFactory.mapByPage { list -> list.map { gifModelMapper.mapFrom(it) } }

        networkState = Transformations.switchMap(dataFactory.sourceLiveData) { it.networkState }
        resultPagedList = LivePagedListBuilder(modelDataFactory, pagedListConfig).build()
    }

    fun refreshResultList() =
        resultPagedList.value?.dataSource?.invalidate()
}