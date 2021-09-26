package com.icodeu.bakeryapp.di.modules

import android.util.Log
import com.icodeu.bakeryapp.network.services.BreadService
import com.icodeu.bakeryapp.network.services.UserService
import com.icodeu.bakeryapp.repositories.UserRepository
import com.icodeu.bakeryapp.utils.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModules = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val userRepo = get<UserRepository>()
                request.addHeader("Accept", "Application/json")
                if (userRepo.getToken() != null) {
                    request.addHeader("Authorization", "Bearer ${userRepo.getToken()}")
                }
                chain.proceed(request.build())
            }
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .baseUrl(Constant.API_URL)
            .client(get())
            .build()
    }
    single {
        get<Retrofit>().create(UserService::class.java)
    }
    single {
        get<Retrofit>().create(BreadService::class.java)
    }
}
