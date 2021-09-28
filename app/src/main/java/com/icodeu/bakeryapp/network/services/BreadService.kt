package com.icodeu.bakeryapp.network.services

import com.icodeu.bakeryapp.models.BreadResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface BreadService {

    @GET("breads/popular")
    suspend fun popular(): Response<BreadResponse>

    @GET("breads/recent")
    suspend fun recent(): Response<BreadResponse>


}