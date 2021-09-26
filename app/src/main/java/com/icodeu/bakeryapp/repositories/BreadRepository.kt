package com.icodeu.bakeryapp.repositories

import com.icodeu.bakeryapp.datastore.user.bread.BreadLocalDataSource
import com.icodeu.bakeryapp.datastore.user.bread.BreadRemoteDataSource
import com.icodeu.bakeryapp.models.Bread

class BreadRepository(
    private val breadLocalDataSource: BreadLocalDataSource,
    private val breadRemoteDataSource: BreadRemoteDataSource
) {

    suspend fun getPopular(): List<Bread> {
        var data = breadLocalDataSource.getPopular()

        if (data.isEmpty()) {
            data = breadRemoteDataSource.getPopular()
        }

        return data
    }

    suspend fun getRecent(): List<Bread> {
        var data = breadLocalDataSource.getRecent()

        if (data.isEmpty()) {
            data = breadRemoteDataSource.getRecent()
        }

        return data
    }

}