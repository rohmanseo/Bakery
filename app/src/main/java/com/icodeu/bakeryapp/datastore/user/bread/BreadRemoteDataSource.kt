package com.icodeu.bakeryapp.datastore.user.bread

import com.icodeu.bakeryapp.models.Bread
import com.icodeu.bakeryapp.network.services.BreadService

class BreadRemoteDataSource(private val breadService: BreadService) {

     suspend fun getPopular(): List<Bread> {
        try {
            val result = breadService.popular()
            if (result.isSuccessful) {
                return result.body()!!.breads
            } else {
                throw Exception(result.message())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

     suspend fun getRecent(): List<Bread> {
        try {
            val result = breadService.recent()
            if (result.isSuccessful) {
                return result.body()!!.breads
            } else {
                throw Exception(result.message())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }


}