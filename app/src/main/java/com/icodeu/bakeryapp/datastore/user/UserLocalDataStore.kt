package com.icodeu.bakeryapp.datastore.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.icodeu.bakeryapp.database.UserDao
import com.icodeu.bakeryapp.models.User

class UserLocalDataStore(private val userDao: UserDao) {
    suspend fun getUser(): User? {
        return userDao.get()
    }

    suspend fun insert(user: User): Boolean {
        userDao.insert(user)
        return getUser() != null
    }
}