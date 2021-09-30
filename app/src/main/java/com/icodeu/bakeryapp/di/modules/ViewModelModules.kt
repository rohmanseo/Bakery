package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.presentation.MainViewModel
import com.icodeu.bakeryapp.presentation.home.HomeViewModel
import com.icodeu.bakeryapp.presentation.login.LoginViewModel
import com.icodeu.bakeryapp.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get(),get(),get()) }
    viewModel { MainViewModel(get(),get(),get()) }
}