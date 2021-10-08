package com.bakeryapp.data.remote.bread

import com.icodeu.bakeryapp.domain.model.Bread

interface BreadRemoteDataSource {
    suspend fun getPopular(): List<Bread>?
    suspend fun getRecent(): List<Bread>?
    suspend fun getSimilar(): List<Bread>?
}