package com.icodeu.bakeryapp.domain.repository

import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun getToken(): String?
    suspend fun login(email: String, password: String): User?
    suspend fun register(
        name: String,
        email: String,
        password: String,
        password_confirmation: String
    ): User?

    suspend fun getLoggedInUser(): Flow<Resource<User?>>
    suspend fun logout(): Boolean
    suspend fun isLoggedIn(): Flow<Resource<Boolean>>
}