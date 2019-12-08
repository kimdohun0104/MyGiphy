package com.dsm.mygiphy.di.detail

import com.dsm.mygiphy.presentation.ui.detail.DetailViewModel
import org.koin.dsl.module

val detailModule = module {

    factory { DetailViewModel(get()) }
}