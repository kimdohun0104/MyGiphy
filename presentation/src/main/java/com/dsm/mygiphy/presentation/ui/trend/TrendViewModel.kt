package com.dsm.mygiphy.presentation.ui.trend

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.domain.usecase.GetTrendListUseCase
import com.dsm.mygiphy.presentation.base.BaseViewModel
import com.dsm.mygiphy.presentation.mapper.GifModelMapper
import com.dsm.mygiphy.presentation.model.GifModel
import com.dsm.mygiphy.presentation.paging.NetworkState
import com.dsm.mygiphy.presentation.paging.trend.TrendDataFactory

class TrendViewModel(
    getTrendListUseCase: GetTrendListUseCase,
    gifModelMapper: GifModelMapper
) : BaseViewModel() {

    val networkState: LiveData<NetworkState>
    val trendPagedList: LiveData<PagedList<GifModel>>

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(25)
            .build()

        val dataFactory = TrendDataFactory(getTrendListUseCase, composite)
        val modelDataFactory = dataFactory.mapByPage { list -> list.map { gifModelMapper.mapFrom(it) } }

        networkState = Transformations.switchMap(dataFactory.sourceLiveData) { it.networkState }
        trendPagedList = LivePagedListBuilder(modelDataFactory, pagedListConfig).build()
    }

    fun refreshTrendList() =
        trendPagedList.value?.dataSource?.invalidate()
}