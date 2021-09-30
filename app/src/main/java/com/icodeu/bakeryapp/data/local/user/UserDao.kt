package com.icodeu.bakeryapp.data.local.user

import androidx.room.*

import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.utils.Resource
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
