package com.icodeu.bakeryapp.data.local.user

import com.icodeu.bakeryapp.domain.model.User

interface UserLocalDataSource {

    suspend fun getUser(): User?
    suspend fun insert(user: User): Boolean

    suspend fun getUserCount(): Int

    suspend fun delete()
}