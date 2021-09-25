package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.network.UserService
import com.icodeu.bakeryapp.network.retrofit
import com.icodeu.bakeryapp.utils.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModules = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .baseUrl(Constant.BASE_URL)
            .build()
    }
    single {
        retrofit.create(UserService::class.java)
    }
}
