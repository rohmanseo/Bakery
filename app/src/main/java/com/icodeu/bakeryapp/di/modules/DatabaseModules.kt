package com.icodeu.bakeryapp.di.modules

import androidx.room.Room
import com.icodeu.bakeryapp.database.AppDatabase
import com.icodeu.bakeryapp.database.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModules = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "bakery").build() }
    single { AppDatabase.instance.userDao() }
}