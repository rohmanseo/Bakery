package com.icodeu.bakeryapp.data.local.user

import com.icodeu.bakeryapp.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {

    override suspend fun getUser(): Flow<User?> {
        return userDao.get().flowOn(Dispatchers.IO)
    }

    override suspend fun insert(user: User): Flow<Boolean> {
        userDao.insert(user)
        return getUserCount().map {
            it != 0
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserCount(): Flow<Int> {
        return userDao.getUserCount().flowOn(Dispatchers.IO)
    }

    override suspend fun delete() {
        userDao.delete()
    }
}