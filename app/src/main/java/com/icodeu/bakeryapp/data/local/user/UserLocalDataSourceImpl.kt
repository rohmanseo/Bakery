package com.icodeu.bakeryapp.data.local.user

import com.icodeu.bakeryapp.domain.model.User

class UserLocalDataSourceImpl(private val userDao: UserDao) :UserLocalDataSource{

    override suspend fun getUser(): User? {
        return userDao.get()
    }

    override suspend fun insert(user: User): Boolean {
        userDao.insert(user)
        return getUserCount() != 0
    }

    override suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }

    override suspend fun delete() {
        userDao.delete()
    }
}