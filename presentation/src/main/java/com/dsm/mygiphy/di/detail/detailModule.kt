package com.dsm.mygiphy.di.detail

import com.dsm.data.dataSource.detail.DetailDataSource
import com.dsm.data.dataSource.detail.DetailDataSourceImpl
import com.dsm.data.mapper.GifDetailEntityMapper
import com.dsm.data.repository.DetailRepositoryImpl
import com.dsm.domain.repository.DetailRepository
import com.dsm.domain.service.DetailService
import com.dsm.domain.service.DetailServiceImpl
import com.dsm.domain.usecase.GetGifDetailUseCase
import com.dsm.domain.usecase.SetFavoriteUseCase
import com.dsm.mygiphy.presentation.mapper.GifDetailModelMapper
import com.dsm.mygiphy.presentation.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {

    factory<DetailDataSource> { DetailDataSourceImpl(get(), get()) }

    factory { GifDetailEntityMapper() }

    factory<DetailRepository> { DetailRepositoryImpl(get(), get()) }

    factory<DetailService> { DetailServiceImpl(get(), get()) }

    factory { GetGifDetailUseCase(get()) }

    factory { SetFavoriteUseCase(get()) }

    factory { GifDetailModelMapper() }

    viewModel { DetailViewModel(get(), get(), get()) }
}