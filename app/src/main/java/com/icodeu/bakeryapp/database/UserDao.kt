package com.icodeu.bakeryapp.database

import androidx.room.*
import com.icodeu.bakeryapp.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    fun get(): Flow<User?>

    @Query("SELECT count(*) FROM user")
    fun getUserCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM `user`")
    suspend fun delete()
}
