package com.icodeu.bakeryapp.data.local.bread

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.icodeu.bakeryapp.domain.model.Bread
import kotlinx.coroutines.flow.Flow

@Dao
interface BreadDao {

    @Query("SELECT * FROM `bread`")
    suspend fun recent(): List<Bread>

    @Query("SELECT * FROM `bread` ORDER BY `views` DESC LIMIT 4")
    suspend fun popular(): List<Bread>

    @Query("SELECT * FROM `bread` LIMIT 8")
    suspend fun similar(): List<Bread>

    @Insert(onConflict = REPLACE)
    suspend fun insert(bread: Bread)

}