package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.ui.login.LoginViewModel
import com.icodeu.bakeryapp.ui.register.RegisterViewModel
import com.icodeu.bakeryapp.ui.splashscreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel{RegisterViewModel(get())}
}