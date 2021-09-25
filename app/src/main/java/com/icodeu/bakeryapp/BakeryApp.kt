package com.icodeu.bakeryapp

import android.app.Application
import com.icodeu.bakeryapp.di.modules.databaseModules
import com.icodeu.bakeryapp.di.modules.networkModules
import com.icodeu.bakeryapp.di.modules.repositories
import com.icodeu.bakeryapp.di.modules.viewModelModules
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
                viewModelModules,
                databaseModules
            )
        }

    }
}