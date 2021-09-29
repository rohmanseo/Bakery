package com.icodeu.bakeryapp.data.remote.services

import com.icodeu.bakeryapp.domain.model.BreadResponse
import retrofit2.Response
import retrofit2.http.GET

interface BreadService {

    @GET("breads/popular")
    suspend fun popular(): Response<BreadResponse>

    @GET("breads/recent")
    suspend fun recent(): Response<BreadResponse>


}