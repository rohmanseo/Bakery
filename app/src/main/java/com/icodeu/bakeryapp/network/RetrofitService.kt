package com.icodeu.bakeryapp.network

import androidx.lifecycle.LiveData
import com.icodeu.bakeryapp.models.SuccessResponse
import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.utils.Constant.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.POST

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LoginService {
    @POST("auth/login")
    fun login(@Field("email") email: String, @Field("password") password: String): LiveData<SuccessResponse>
}

object LoginApi {
    val retrofitService: LoginService by lazy { retrofit.create(LoginService::class.java) }
}