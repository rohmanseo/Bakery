package com.icodeu.bakeryapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.icodeu.bakeryapp.models.Bread
import kotlinx.coroutines.flow.Flow

@Dao
interface BreadDao {

    @Query("SELECT * FROM `bread`")
    fun recent(): Flow<List<Bread>>

    @Query("SELECT * FROM `bread` ORDER BY `views` DESC LIMIT 4")
    fun popular(): Flow<List<Bread>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(bread: Bread)

}