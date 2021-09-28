package com.icodeu.bakeryapp.repositories

import com.icodeu.bakeryapp.datastore.user.user.TokenHolder
import com.icodeu.bakeryapp.datastore.user.user.UserLocalDataStore
import com.icodeu.bakeryapp.datastore.user.user.UserRemoteDataStore
import com.icodeu.bakeryapp.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class UserRepository(
    private val userRemoteDataStore: UserRemoteDataStore,
    private val userLocalDataStore: UserLocalDataStore
) {
    private val tokenHolder = TokenHolder()

    fun getToken(): String? {
        return tokenHolder.token
    }

    suspend fun login(email: String, password: String): User? {
        val user: User? = userRemoteDataStore.login(email, password)
        if (user != null) {
            userLocalDataStore.insert(user)
            tokenHolder.token = user.token
        }
        return user
    }

    suspend fun register(
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

    fun getLoggedInUser(): Flow<User?> {
        return userLocalDataStore.getUser().onEach {
            if (it !=null){
                tokenHolder.token = it.token
            }
        }
    }

    suspend fun logout(): Boolean {
        userRemoteDataStore.logout()
        userLocalDataStore.delete()
        tokenHolder.token = null
        return true
    }

    fun isLoggedIn(): Flow<Boolean> {
        return userLocalDataStore.getUserCount().map { it!=0 }
    }
}