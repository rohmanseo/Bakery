package com.icodeu.bakeryapp.di.modules

import androidx.room.Room
import com.icodeu.bakeryapp.data.local.AppDatabase
import org.koin.dsl.module

val databaseModules = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "bakery").build() }
    single{(get(AppDatabase::class) as AppDatabase).breadDao()}
    single{(get(AppDatabase::class) as AppDatabase).userDao()}
}