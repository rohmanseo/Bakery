package com.icodeu.bakeryapp

import android.app.Application
import com.icodeu.bakeryapp.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BakeryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
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