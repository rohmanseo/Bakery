package com.icodeu.bakeryapp.database

import androidx.room.*
import com.icodeu.bakeryapp.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun get():User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}
