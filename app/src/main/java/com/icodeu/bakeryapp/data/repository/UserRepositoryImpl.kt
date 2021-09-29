package com.icodeu.bakeryapp.data.repository

import com.icodeu.bakeryapp.data.local.user.TokenHolder
import com.icodeu.bakeryapp.data.local.user.UserLocalDataStore
import com.icodeu.bakeryapp.data.remote.user.UserRemoteDataSource
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataStore: UserRemoteDataSource,
    private val userLocalDataStore: UserLocalDataStore
) : UserRepository {
    private val tokenHolder = TokenHolder()

    override fun getToken(): String? {
        return tokenHolder.token
    }

    override suspend fun login(email: String, password: String): User? {
        val user: User? = userRemoteDataStore.login(email, password)
        if (user != null) {
            userLocalDataStore.insert(user)
            tokenHolder.token = user.token
        }
        return user
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        password_confirmation: String
    ): User? {
        return userRemoteDataStore.register(
            name = name,
            email = email,
            password = password,
            password_confirmation = password_confirmation
        )
    }

    override suspend fun getLoggedInUser(): User? {
        val user = userLocalDataStore.getUser()
        user?.token.let { token ->
            tokenHolder.token = token
        }
        return user
    }

    override suspend fun logout(): Boolean {
        userRemoteDataStore.logout()
        userLocalDataStore.delete()
        tokenHolder.token = null
        return true
    }

    override suspend fun isLoggedIn(): Boolean {
        return userLocalDataStore.getUserCount() != 0
    }
}