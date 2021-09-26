package com.icodeu.bakeryapp.database

import androidx.room.Dao
import androidx.room.Query
import com.icodeu.bakeryapp.models.Bread

@Dao
interface BreadDao {

    @Query("SELECT * FROM `bread`")
    suspend fun recent(): List<Bread>

    @Query("SELECT * FROM `bread` ORDER BY `views` DESC LIMIT 4")
    suspend fun popular(): List<Bread>

}