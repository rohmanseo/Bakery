package com.icodeu.bakeryapp.repositories

import com.icodeu.bakeryapp.database.AppDatabase
import com.icodeu.bakeryapp.database.UserDao
import com.icodeu.bakeryapp.network.LoginService
import com.icodeu.bakeryapp.ui.login.LoginState
import com.icodeu.bakeryapp.utils.UIState
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

class UserRepository(val userDao: UserDao) {

    suspend fun login(email: String, password: String): LoginState {
        val loginService: LoginService by inject(LoginService::class.java)
        val loginResponse = loginService.login(email, password)

        if (loginResponse.isSuccessful) {
            loginResponse.body()?.let { userDao.create(it.user) }
            return LoginState(UIState.STATE_SUCCESS, null, loginResponse.body())
        } else {
            return LoginState(UIState.STATE_ERROR, loginResponse.message())
        }
    }

    suspend fun isLoggedIn(): Boolean {
        val user = userDao.get()
        return user != null
    }
}