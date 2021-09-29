package com.icodeu.bakeryapp.data.repository

import com.icodeu.bakeryapp.data.local.bread.BreadLocalDataSource
import com.icodeu.bakeryapp.data.remote.bread.BreadRemoteDataSource
import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.domain.repository.BreadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

    override suspend fun getRecent(): List<Bread> {
            val remoteData = breadRemoteDataSource.getRecent()
            if (remoteData != null) {
                breadLocalDataSource.insert(remoteData)
            }
        return breadLocalDataSource.getRecent()
    }

}