package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.repositories.UserRepository
import org.koin.dsl.module

val repositories = module {
    single { UserRepository(get()) }
}