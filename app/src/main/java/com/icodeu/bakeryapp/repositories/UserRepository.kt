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

    suspend fun isLoggedIn(): Boolean {
        println("is logged in repo: ${userLocalDataStore.getUser()}")
        return userLocalDataStore.getUser() != null
    }
}