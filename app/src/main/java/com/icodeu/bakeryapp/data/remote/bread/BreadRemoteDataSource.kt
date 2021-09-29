package com.icodeu.bakeryapp.data.remote.bread

import com.icodeu.bakeryapp.data.remote.services.BreadService
import com.icodeu.bakeryapp.domain.model.Bread

class BreadRemoteDataSource(private val breadService: BreadService) {

    suspend fun getPopular(): List<Bread>? {
        val result = breadService.popular()
        if (result.isSuccessful) {
            return result.body()?.breads
        } else {
            throw Exception(result.message())
        }
    }

    suspend fun getRecent(): List<Bread>? {
        val result = breadService.recent()
        if (result.isSuccessful) {
            return result.body()?.breads
        } else {
            throw Exception(result.message())
        }
    }
}
