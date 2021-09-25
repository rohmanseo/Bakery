package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.datastore.user.UserLocalDataStore
import com.icodeu.bakeryapp.datastore.user.UserRemoteDataStore
import com.icodeu.bakeryapp.repositories.UserRepository
import org.koin.dsl.module

val repositories = module {
    single { UserRemoteDataStore(get()) }
    single { UserLocalDataStore(get()) }
    single { UserRepository(get(),get()) }
}