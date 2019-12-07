package com.dsm.mygiphy.di

import androidx.room.Room
import com.dsm.data.local.AppDataBase
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(get(), AppDataBase::class.java, "gifphy.db")
            .allowMainThreadQueries()
            .build()
    }

    factory { get<AppDataBase>().gifDao() }
}