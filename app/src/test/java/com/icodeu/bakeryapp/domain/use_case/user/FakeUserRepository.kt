package com.icodeu.bakeryapp.domain.use_case.user

import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.repository.UserRepository
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FakeUserRepository : UserRepository {

    companion object {
        val validUser: User = User(1, "rohman", "rohman@gmail.com", "empty")
        val validPassword = "password"
    }


    override fun getToken(): String? {
        return null
    }

    override suspend fun login(email: String, password: String): User? {
        if (password == validPassword && email == validUser.email) {
            return validUser
        } else {
            throw Exception("invalid user")
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        password_confirmation: String
    ): User? {
        return User(id = 1, name = name, email = email, token = "empty")
    }

    override suspend fun getLoggedInUser(): Flow<Resource<User?>> {
        return flow {
            emit(validUser)
        }.map {
            Resource.Success(it)
        }
    }

    override suspend fun logout(): Boolean {
        return true
    }

    override suspend fun isLoggedIn(): Flow<Resource<Boolean>> {
        return flow {
            emit(true)
        }.map {
            Resource.Success(it)
        }
    }

}
