package com.icodeu.bakeryapp

import android.app.Application
import com.icodeu.bakeryapp.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger

class BakeryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BakeryApp)
            modules(
                networkModules,
                repositories,
                useCases,
                viewModelModules,
                databaseModules,
            )
        }

    }
}