package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.data.local.bread.BreadLocalDataSource
import com.icodeu.bakeryapp.data.local.bread.BreadLocalDataSourceImpl
import com.icodeu.bakeryapp.data.local.user.UserLocalDataSource
import com.icodeu.bakeryapp.data.local.user.UserLocalDataSourceImpl
import com.icodeu.bakeryapp.data.remote.bread.BreadRemoteDataSource
import com.icodeu.bakeryapp.data.remote.bread.BreadRemoteDataSourceImpl
import com.icodeu.bakeryapp.data.remote.user.UserRemoteDataSource
import com.icodeu.bakeryapp.data.remote.user.UserRemoteDataSourceImpl
import com.icodeu.bakeryapp.data.repository.BreadRepositoryImpl
import com.icodeu.bakeryapp.data.repository.UserRepositoryImpl
import com.icodeu.bakeryapp.domain.repository.BreadRepository
import com.icodeu.bakeryapp.domain.repository.UserRepository
import org.koin.dsl.module

val repositories = module {
    single<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(
            get()
        )
    }
    single<UserLocalDataSource> {
        UserLocalDataSourceImpl(
            get()
        )
    }
    single<UserRepository> {
        UserRepositoryImpl(
            get(),
            get()
        )
    }

    single<BreadRemoteDataSource> {
        BreadRemoteDataSourceImpl(
            get()
        )
    }
    single<BreadLocalDataSource> {
        BreadLocalDataSourceImpl(
            get()
        )
    }
    single<BreadRepository> {
        BreadRepositoryImpl(
            get(),
            get()
        )
    }
}