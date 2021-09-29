package com.icodeu.bakeryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.icodeu.bakeryapp.data.local.bread.BreadDao
import com.icodeu.bakeryapp.data.local.user.UserDao
import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.domain.model.User
import org.koin.java.KoinJavaComponent.inject

@Database(entities = [User::class, Bread::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun breadDao(): BreadDao

    companion object {
        val instance: AppDatabase by inject(AppDatabase::class.java)
    }
}
