package com.icodeu.bakeryapp.datastore.user.bread

import com.icodeu.bakeryapp.database.BreadDao
import com.icodeu.bakeryapp.models.Bread

class BreadLocalDataSource(private val breadDao: BreadDao) {
     suspend fun getPopular(): List<Bread> {
        return breadDao.popular()
    }

     suspend fun getRecent(): List<Bread> {
        return breadDao.recent()
    }


}