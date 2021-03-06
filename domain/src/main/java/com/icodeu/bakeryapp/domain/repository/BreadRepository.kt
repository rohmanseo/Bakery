package com.icodeu.bakeryapp.domain.repository

import com.icodeu.bakeryapp.domain.model.Bread
import kotlinx.coroutines.flow.Flow

interface BreadRepository {
    suspend fun getRecent(): List<Bread>
    suspend fun getRecentCache(): List<Bread>
    suspend fun getPopular(): List<Bread>
    suspend fun getPopularCache(): List<Bread>
    suspend fun getSimilar(): List<Bread>
    suspend fun getSimilarCache(): List<Bread>
}