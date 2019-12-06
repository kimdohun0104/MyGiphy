package com.dsm.mygiphy.di

import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.ErrorHandlerImpl
import org.koin.dsl.module

val appModule = module {

    factory<ErrorHandler> { ErrorHandlerImpl() }
}