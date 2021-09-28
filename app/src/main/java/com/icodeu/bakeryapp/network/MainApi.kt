package com.icodeu.bakeryapp.network

import okhttp3.OkHttpClient
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit

val retrofit: Retrofit by inject(Retrofit::class.java)

