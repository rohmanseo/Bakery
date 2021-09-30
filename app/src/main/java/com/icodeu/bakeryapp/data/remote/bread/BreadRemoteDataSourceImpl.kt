package com.icodeu.bakeryapp.data.remote.bread

import com.icodeu.bakeryapp.data.remote.services.BreadService
import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.utils.HttpErrorCode

class BreadRemoteDataSourceImpl(private val breadService: BreadService):BreadRemoteDataSource {

     override suspend fun getPopular(): List<Bread>? {
        val result = breadService.popular()
        if (result.isSuccessful) {
            return result.body()?.breads
        } else {
            throw Exception(HttpErrorCode.getErrorMessage(result.code()))
        }
    }

     override suspend fun getRecent(): List<Bread>? {
        val result = breadService.recent()
        if (result.isSuccessful) {
            return result.body()?.breads
        } else {
            throw Exception(HttpErrorCode.getErrorMessage(result.code()))
        }
    }
}
