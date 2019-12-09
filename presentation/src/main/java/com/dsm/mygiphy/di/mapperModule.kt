package com.dsm.mygiphy.di

import com.dsm.data.mapper.GifEntityMapper
import com.dsm.mygiphy.presentation.mapper.GifModelMapper
import org.koin.dsl.module

val mapperModule = module {

    factory { GifEntityMapper() }

    factory { GifModelMapper() }
}