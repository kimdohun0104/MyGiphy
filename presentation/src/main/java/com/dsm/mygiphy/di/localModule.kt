package com.dsm.mygiphy.di

import androidx.room.Room
import com.dsm.data.local.AppDataBase
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(get(), AppDataBase::class.java, "giphy.db")
            .allowMainThreadQueries()
            .build()
    }

    single { get<AppDataBase>().gifDao() }

    factory { get<AppDataBase>().searchHistoryDao() }
}