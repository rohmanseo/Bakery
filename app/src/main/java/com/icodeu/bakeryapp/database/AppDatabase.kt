package com.icodeu.bakeryapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.icodeu.bakeryapp.models.Bread
import com.icodeu.bakeryapp.models.User
import org.koin.java.KoinJavaComponent.inject

@Database(entities = [User::class, Bread::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun breadDao(): BreadDao

    companion object {
        val instance: AppDatabase by inject(AppDatabase::class.java)
    }
}
