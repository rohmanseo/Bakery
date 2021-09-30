package com.icodeu.bakeryapp.data.remote.user

import com.icodeu.bakeryapp.data.remote.services.UserService
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.utils.HttpErrorCode


class UserRemoteDataSourceImpl(private val userService: UserService) :UserRemoteDataSource{

     override suspend fun register(
        name: String,
        email: String,
        password: String,
        password_confirmation: String
    ): User? {

        val registerResponse = userService.register(
            name = name,
            email = email,
            password = password,
            password_confirmation = password_confirmation
        )
        if (registerResponse.isSuccessful) {
            return registerResponse.body()?.user
        } else {
            throw Exception(HttpErrorCode.getErrorMessage(registerResponse.code()))
        }
    }

     override suspend fun logout(): Boolean {
        val logoutResponse = userService.logout()
        if (logoutResponse.isSuccessful) {
            return true
        } else {
            throw Exception(HttpErrorCode.getErrorMessage(logoutResponse.code()))
        }
    }

     override suspend fun login(email: String, password: String): User? {

        val loginResponse = userService.login(email, password)

        if (loginResponse.isSuccessful) {
            return loginResponse.body()?.user
        } else {
            throw Exception(HttpErrorCode.getErrorMessage(loginResponse.code()))
        }
    }
}