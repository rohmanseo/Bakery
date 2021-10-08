package com.bakeryapp.data.remote.services

import com.icodeu.bakeryapp.domain.model.SimpleSuccessResponse
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface UserService {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<UserResponse>

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
    ): Response<UserResponse>

    @POST("auth/logout")
    suspend fun logout():Response<SimpleSuccessResponse>

    @GET("auth/refreshToken")
    fun refreshToken(): Call<User>


}
