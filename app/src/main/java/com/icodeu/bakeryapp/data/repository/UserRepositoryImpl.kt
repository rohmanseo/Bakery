package com.icodeu.bakeryapp.data.repository

import com.icodeu.bakeryapp.data.local.user.TokenHolder
import com.icodeu.bakeryapp.data.local.user.UserLocalDataSource
import com.icodeu.bakeryapp.data.remote.user.UserRemoteDataSource
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
) : UserRepository {
    private val tokenHolder = TokenHolder()

    override fun getToken(): String? {
        return tokenHolder.token
    }

    override suspend fun login(email: String, password: String): User? {
        val user: User? = remoteDataSource.login(email, password)
        if (user != null) {
            userLocalDataSource.insert(user)
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
        return remoteDataSource.register(
            name = name,
            email = email,
            password = password,
            password_confirmation = password_confirmation
        )
    }

    override suspend fun getLoggedInUser(): User? {
        val user = userLocalDataSource.getUser()
        user?.token.let { token ->
            tokenHolder.token = token
        }
        return user
    }

    override suspend fun logout(): Boolean {
        userLocalDataSource.delete()
        tokenHolder.token = null
        remoteDataSource.logout()
        return true
    }

    override suspend fun isLoggedIn(): Boolean {
        return userLocalDataSource.getUserCount() != 0
    }
}