package com.dsm.mygiphy.presentation

import android.app.Application
import com.dsm.mygiphy.di.appModule
import com.dsm.mygiphy.di.detail.detailModule
import com.dsm.mygiphy.di.localModule
import com.dsm.mygiphy.di.networkModule
import com.dsm.mygiphy.di.trend.trendModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApp)
            modules(
                listOf(
                    localModule,
                    networkModule,
                    trendModule,
                    appModule,
                    detailModule
                )
            )
        }
    }
}