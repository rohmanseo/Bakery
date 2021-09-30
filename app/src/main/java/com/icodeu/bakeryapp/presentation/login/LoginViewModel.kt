package com.icodeu.bakeryapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.use_case.user.LoginUseCase
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUserUseCase: LoginUseCase
) : ViewModel() {

    private var _user = MutableSharedFlow<Resource<User>>()
    val user: SharedFlow<Resource<User>>
        get() = _user

    fun login(email: String, password: String)=viewModelScope.launch {
            loginUserUseCase(email, password).collect {
                println("Login called")
                _user.emit(it)
            }
    }
}