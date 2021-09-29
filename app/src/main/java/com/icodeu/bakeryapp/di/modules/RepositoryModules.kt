package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.data.local.bread.BreadLocalDataSource
import com.icodeu.bakeryapp.data.remote.bread.BreadRemoteDataSource
import com.icodeu.bakeryapp.data.local.user.UserLocalDataStore
import com.icodeu.bakeryapp.data.remote.user.UserRemoteDataSource
import com.icodeu.bakeryapp.data.repository.BreadRepositoryImpl
import com.icodeu.bakeryapp.data.repository.UserRepositoryImpl
import com.icodeu.bakeryapp.domain.repository.BreadRepository
import com.icodeu.bakeryapp.domain.repository.UserRepository
import org.koin.dsl.module

val repositories = module {
    single { UserRemoteDataSource(get()) }
    single { UserLocalDataStore(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    single { BreadRemoteDataSource(get()) }
    single { BreadLocalDataSource(get()) }
    single<BreadRepository> { BreadRepositoryImpl(get(), get()) }
}