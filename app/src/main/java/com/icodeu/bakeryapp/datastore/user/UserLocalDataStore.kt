package com.icodeu.bakeryapp.datastore.user

import com.icodeu.bakeryapp.database.UserDao
import com.icodeu.bakeryapp.models.User

class UserLocalDataStore(private val userDao: UserDao) {
    suspend fun getUser(): User {
        return userDao.get()
    }

    suspend fun insert(user: User): Boolean {
        userDao.insert(user)
        return getUserCount() != 0
    }

    suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }
}