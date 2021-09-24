package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
}