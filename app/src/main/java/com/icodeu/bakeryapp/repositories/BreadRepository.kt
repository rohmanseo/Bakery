package com.icodeu.bakeryapp.repositories

import com.icodeu.bakeryapp.datastore.user.bread.BreadLocalDataSource
import com.icodeu.bakeryapp.datastore.user.bread.BreadRemoteDataSource
import com.icodeu.bakeryapp.models.Bread
import kotlinx.coroutines.flow.Flow

class BreadRepository(
    private val breadLocalDataSource: BreadLocalDataSource,
    private val breadRemoteDataSource: BreadRemoteDataSource
) {

    fun getPopular(): Flow<List<Bread>> {
        return breadLocalDataSource.getPopular()
    }

    fun getRecent(): Flow<List<Bread>> {
        return breadLocalDataSource.getRecent()
    }

}