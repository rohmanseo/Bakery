package com.icodeu.bakeryapp.data.local.user

import androidx.room.*
import com.icodeu.bakeryapp.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun get():User?

    @Query("SELECT count(*) FROM user")
    suspend fun getUserCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM `user`")
    suspend fun delete()
}
