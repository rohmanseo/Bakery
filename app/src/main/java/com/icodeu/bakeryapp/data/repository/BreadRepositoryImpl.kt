package com.icodeu.bakeryapp.data.repository

import com.icodeu.bakeryapp.data.local.bread.BreadLocalDataSource
import com.icodeu.bakeryapp.data.remote.bread.BreadRemoteDataSource
import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.domain.repository.BreadRepository

class BreadRepositoryImpl(
    private val breadLocalDataSource: BreadLocalDataSource,
    private val breadRemoteDataSource: BreadRemoteDataSource
) :BreadRepository{

    override suspend fun getPopular(): List<Bread> {
        val remoteData = breadRemoteDataSource.getPopular()
        if (!remoteData.isNullOrEmpty()){
            breadLocalDataSource.insert(remoteData)
        }
        return breadLocalDataSource.getPopular()
    }

    override suspend fun getPopularCache(): List<Bread> {
        return breadLocalDataSource.getPopular()
    }

    override suspend fun getRecent(): List<Bread> {
            val remoteData = breadRemoteDataSource.getRecent()
            if (remoteData != null) {
                breadLocalDataSource.insert(remoteData)
            }
        return breadLocalDataSource.getRecent()
    }

    override suspend fun getRecentCache(): List<Bread> {
        return breadLocalDataSource.getRecent()
    }

}