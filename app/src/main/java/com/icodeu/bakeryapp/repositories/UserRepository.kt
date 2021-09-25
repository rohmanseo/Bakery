package com.icodeu.bakeryapp.repositories

import com.icodeu.bakeryapp.datastore.user.UserLocalDataStore
import com.icodeu.bakeryapp.datastore.user.UserRemoteDataStore
import com.icodeu.bakeryapp.models.User

class UserRepository(
    private val userRemoteDataStore: UserRemoteDataStore,
    private val userLocalDataStore: UserLocalDataStore
) {

    suspend fun login(email: String, password: String): User? {
        val user: User? = userRemoteDataStore.login(email, password)
        if (user != null) {
            userLocalDataStore.insert(user)
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

    suspend fun getLoggedInUser(): User {
        return userLocalDataStore.getUser()
    }

    suspend fun logout(): Boolean {
        val user = userLocalDataStore.getUser()
        val response = userRemoteDataStore.logout("Bearer ${user.token}")
        if (response){
            userLocalDataStore.delete(user)
        }
        return response
    }

    suspend fun isLoggedIn(): Boolean {
        return userLocalDataStore.getUserCount() != 0
    }
}