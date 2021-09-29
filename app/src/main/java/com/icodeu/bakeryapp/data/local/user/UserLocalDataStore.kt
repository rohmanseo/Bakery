package com.icodeu.bakeryapp.data.local.user

import com.icodeu.bakeryapp.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class UserLocalDataStore(private val userDao: UserDao) {

    suspend fun getUser(): User? {
        return userDao.get()
    }

    suspend fun insert(user: User): Boolean {
        userDao.insert(user)
        return getUserCount() != 0
    }

    suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }

    suspend fun delete() {
        userDao.delete()
    }
}