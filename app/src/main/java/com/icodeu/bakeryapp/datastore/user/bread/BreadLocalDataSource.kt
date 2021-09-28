package com.icodeu.bakeryapp.datastore.user.bread

import com.icodeu.bakeryapp.database.BreadDao
import com.icodeu.bakeryapp.models.Bread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class BreadLocalDataSource(private val breadDao: BreadDao) {
    fun getPopular(): Flow<List<Bread>> {
        return breadDao.popular().flowOn(Dispatchers.IO)
    }

    fun getRecent(): Flow<List<Bread>> {
        return breadDao.recent().flowOn(Dispatchers.IO)
    }

    fun update() {

    }

    suspend fun insert(breads: List<Bread>) {
        breads.forEach {
            breadDao.insert(it)
        }
    }


}