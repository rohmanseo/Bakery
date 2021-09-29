package com.icodeu.bakeryapp.domain.repository

import com.icodeu.bakeryapp.domain.model.User

interface UserRepository {
    fun getToken(): String?
    suspend fun login(email: String, password: String): User?
    suspend fun register(
        name: String,
        email: String,
        password: String,
        password_confirmation: String
    ): User?

    suspend fun getLoggedInUser(): User?
    suspend fun logout(): Boolean
    suspend fun isLoggedIn(): Boolean
}