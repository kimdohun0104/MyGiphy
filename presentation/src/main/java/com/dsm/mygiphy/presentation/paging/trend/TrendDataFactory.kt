package com.dsm.mygiphy.presentation.paging.trend

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dsm.domain.entity.GifEntity
import com.dsm.domain.usecase.GetTrendListUseCase

class TrendDataFactory(
    private val getTrendListUseCase: GetTrendListUseCase
) : DataSource.Factory<Int, GifEntity>() {

    val sourceLiveData = MutableLiveData<TrendKeyedDataSource>()

    override fun create(): DataSource<Int, GifEntity> {
        TrendKeyedDataSource(getTrendListUseCase).let {
            sourceLiveData.postValue(it)
            return it
        }
    }
}