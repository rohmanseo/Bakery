package com.icodeu.bakeryapp.data.remote.user

import com.icodeu.bakeryapp.data.remote.services.UserService
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.utils.HttpErrorCode


interface UserRemoteDataSource {

     suspend fun register(
        name: String,
        email: String,
        password: String,
        password_confirmation: String
    ): User?

     suspend fun logout(): Boolean

     suspend fun login(email: String, password: String): User?
}