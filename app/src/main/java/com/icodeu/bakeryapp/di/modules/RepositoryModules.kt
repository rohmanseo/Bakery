package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.datastore.user.bread.BreadLocalDataSource
import com.icodeu.bakeryapp.datastore.user.bread.BreadRemoteDataSource
import com.icodeu.bakeryapp.datastore.user.user.UserLocalDataStore
import com.icodeu.bakeryapp.datastore.user.user.UserRemoteDataStore
import com.icodeu.bakeryapp.repositories.BreadRepository
import com.icodeu.bakeryapp.repositories.UserRepository
import org.koin.dsl.module

val repositories = module {
    single { UserRemoteDataStore(get()) }
    single { UserLocalDataStore(get()) }
    single { UserRepository(get(), get()) }

    single { BreadRemoteDataSource(get()) }
    single { BreadLocalDataSource(get()) }
    single { BreadRepository(get(), get()) }
}