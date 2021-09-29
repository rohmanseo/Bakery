package com.icodeu.bakeryapp.domain.repository

import com.icodeu.bakeryapp.domain.model.Bread
import kotlinx.coroutines.flow.Flow

interface BreadRepository {
    suspend fun getRecent(): List<Bread>
    suspend fun getPopular(): List<Bread>
}