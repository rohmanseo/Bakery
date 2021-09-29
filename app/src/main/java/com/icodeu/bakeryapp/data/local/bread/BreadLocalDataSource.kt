package com.icodeu.bakeryapp.data.local.bread

import com.icodeu.bakeryapp.domain.model.Bread
import kotlinx.coroutines.flow.Flow

class BreadLocalDataSource(private val breadDao: BreadDao) {
    suspend fun getPopular(): List<Bread> {
        return breadDao.popular()
    }

    suspend fun getRecent(): List<Bread> {
        return breadDao.recent()
    }

    fun update() {

    }

    suspend fun insert(breads: List<Bread>) {
        breads.forEach {
            breadDao.insert(it)
        }
    }


}