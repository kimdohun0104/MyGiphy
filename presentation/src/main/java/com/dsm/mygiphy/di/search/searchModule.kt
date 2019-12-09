package com.dsm.mygiphy.di.search

import com.dsm.data.dataSource.search.SearchDataSource
import com.dsm.data.dataSource.search.SearchDataSourceImpl
import com.dsm.data.repository.SearchRepositoryImpl
import com.dsm.domain.repository.SearchRepository
import com.dsm.domain.service.SearchService
import com.dsm.domain.service.SearchServiceImpl
import com.dsm.domain.usecase.SearchGifListUseCase
import com.dsm.mygiphy.presentation.ui.search.SearchViewModel
import com.dsm.mygiphy.presentation.ui.search.searchResult.SearchResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    factory<SearchDataSource> { SearchDataSourceImpl(get(), get()) }

    factory<SearchRepository> { SearchRepositoryImpl(get(), get()) }

    factory<SearchService> { SearchServiceImpl(get(), get()) }

    // search result
    factory { SearchGifListUseCase(get()) }

    viewModel { (search: String) -> SearchResultViewModel(get(), get(), search) }

    // search
    viewModel { SearchViewModel(get()) }
}