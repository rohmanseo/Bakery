package com.icodeu.bakeryapp.data.remote

import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.data.remote.services.BreadService

class BreadRemoteDataSource(private val breadService: BreadService) {

     suspend fun getPopular(): List<Bread>? {
        try {
            val result = breadService.popular()
            if (result.isSuccessful) {
                return result.body()?.breads
            } else {
                throw Exception(result.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e.message)
        }
    }

     suspend fun getRecent(): List<Bread>? {
        try {
            val result = breadService.recent()
            if (result.isSuccessful) {
                return result.body()?.breads
            } else {
                throw Exception(result.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e.message)
        }
    }


}