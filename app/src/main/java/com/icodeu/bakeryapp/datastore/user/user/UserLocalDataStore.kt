package com.icodeu.bakeryapp.datastore.user.user

import com.icodeu.bakeryapp.database.UserDao
import com.icodeu.bakeryapp.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserLocalDataStore(private val userDao: UserDao) {

    fun getUser(): Flow<User?> {
        return userDao.get().flowOn(Dispatchers.IO)
    }

    suspend fun insert(user: User): Flow<Boolean> {
        userDao.insert(user)
        return getUserCount().map { it != 0 }.flowOn(Dispatchers.IO)
    }

    fun getUserCount(): Flow<Int> {
        return userDao.getUserCount().flowOn(Dispatchers.IO)
    }

    suspend fun delete() {
        userDao.delete()
    }
}