package com.icodeu.bakeryapp.data.local.bread

import com.icodeu.bakeryapp.domain.model.Bread
import kotlinx.coroutines.flow.Flow

interface BreadLocalDataSource {
    suspend fun getPopular(): List<Bread>
    suspend fun getRecent(): List<Bread>

    fun update()
    suspend fun insert(breads: List<Bread>)


}