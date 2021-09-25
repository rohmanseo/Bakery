package com.icodeu.bakeryapp.datastore.user

import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.network.UserService


class UserRemoteDataStore(private val userService: UserService) {

    suspend fun register(
        name: String,
        email: String,
        password: String,
        password_confirmation: String
    ): User? {

        try {
            val registerResponse = userService.register(
                name = name,
                email = email,
                password = password,
                password_confirmation = password_confirmation
            )
            if (registerResponse.isSuccessful) {
                return registerResponse.body()?.user
            } else {
                throw Exception(registerResponse.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e.message)
        }
    }

    suspend fun logout(token: String): Boolean {
        try {
            val logoutResponse = userService.logout(token)
            if (logoutResponse.isSuccessful) {
                return true
            } else {
                throw Exception(logoutResponse.message())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    suspend fun login(email: String, password: String): User? {
        try {
            val loginResponse = userService.login(email, password)

            if (loginResponse.isSuccessful) {
                return loginResponse.body()?.user
            } else {
                throw Exception(loginResponse.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception(e.message)
        }
    }
}