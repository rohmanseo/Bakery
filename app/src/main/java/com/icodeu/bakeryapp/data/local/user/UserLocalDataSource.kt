package com.icodeu.bakeryapp.data.local.user

import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun getUser(): Flow<User?>
    suspend fun insert(user: User): Flow<Boolean>

    suspend fun getUserCount(): Flow<Int>

    suspend fun delete()
}