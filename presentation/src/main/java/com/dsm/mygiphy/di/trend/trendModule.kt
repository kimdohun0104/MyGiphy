package com.dsm.mygiphy.di.trend

import com.dsm.data.dataSource.TrendDataSource
import com.dsm.data.dataSource.TrendDataSourceImpl
import com.dsm.data.mapper.GifDataMapper
import com.dsm.data.repository.TrendRepositoryImpl
import com.dsm.domain.repository.TrendRepository
import com.dsm.domain.service.TrendService
import com.dsm.domain.service.TrendServiceImpl
import com.dsm.domain.usecase.GetTrendListUseCase
import com.dsm.mygiphy.presentation.mapper.GifModelMapper
import com.dsm.mygiphy.presentation.ui.trend.TrendViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trendModule = module {

    factory<TrendDataSource> { TrendDataSourceImpl(get(), get()) }

    factory { GifDataMapper() }

    factory<TrendRepository> { TrendRepositoryImpl(get(), get()) }

    factory<TrendService> { TrendServiceImpl(get(), get()) }

    factory { GetTrendListUseCase(get()) }

    factory { GifModelMapper() }

    viewModel { TrendViewModel(get(), get()) }
}