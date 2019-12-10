package com.dsm.mygiphy.di

import com.dsm.data.ErrorHandlerImpl
import com.dsm.domain.error.ErrorHandler
import org.koin.dsl.module

val appModule = module {

    factory<ErrorHandler> { ErrorHandlerImpl() }
}