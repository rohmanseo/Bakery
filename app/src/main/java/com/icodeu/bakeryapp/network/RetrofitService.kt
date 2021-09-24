package com.icodeu.bakeryapp.network

import com.icodeu.bakeryapp.models.SuccessResponse
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

val retrofit: Retrofit by inject(Retrofit::class.java)

interface LoginService {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<SuccessResponse>
}