package com.icodeu.bakeryapp.di.modules

import com.icodeu.bakeryapp.domain.use_case.bread.GetPopularBreadUseCase
import com.icodeu.bakeryapp.domain.use_case.bread.GetRecentBreadUseCase
import com.icodeu.bakeryapp.domain.use_case.user.*
import org.koin.dsl.module

val useCases = module {
    single {
        GetPopularBreadUseCase(get())
    }
    single {
        GetRecentBreadUseCase(get())
    }


    single {
        GetUserUseCase(get())
    }
    single {
        IsLoggedInUseCase(get())
    }
    single {
        LoginUseCase(get())
    }
    single {
        LogoutUseCase(get())
    }
    single {
        RegisterUseCase(get())
    }
}