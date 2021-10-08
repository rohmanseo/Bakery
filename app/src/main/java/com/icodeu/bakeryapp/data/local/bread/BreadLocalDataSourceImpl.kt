package com.icodeu.bakeryapp.data.local.bread

import com.icodeu.bakeryapp.domain.model.Bread
import kotlinx.coroutines.flow.Flow

class BreadLocalDataSourceImpl(private val breadDao: BreadDao):BreadLocalDataSource {
    override suspend fun getPopular(): List<Bread> {
        return breadDao.popular()
    }

    override suspend fun getRecent(): List<Bread> {
        return breadDao.recent()
    }

    override suspend fun getSimilar(): List<Bread> {
        return breadDao.similar()
    }

    override fun update() {

    }

    override suspend fun insert(breads: List<Bread>) {
        breads.forEach {
            breadDao.insert(it)
        }
    }


}