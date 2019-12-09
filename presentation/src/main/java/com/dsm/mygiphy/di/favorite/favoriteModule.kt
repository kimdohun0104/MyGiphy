package com.dsm.mygiphy.di.favorite

import com.dsm.mygiphy.presentation.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    viewModel { FavoriteViewModel(get(), get()) }
}