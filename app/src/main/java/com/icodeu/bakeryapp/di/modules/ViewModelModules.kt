package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.ui.home.HomeViewModel
import com.icodeu.bakeryapp.ui.login.LoginViewModel
import com.icodeu.bakeryapp.ui.register.RegisterViewModel
import com.icodeu.bakeryapp.ui.splashscreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { SplashScreenViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get(),get()) }
}