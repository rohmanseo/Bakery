package com.icodeu.bakeryapp.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.core.utils.Resource
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.use_case.user.RegisterUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUseCase) :
    ViewModel() {

    private var _user = MutableSharedFlow<Resource<User>>()
    val user: SharedFlow<Resource<User>>
        get() = _user

    fun register(name: String, email: String, password: String, password_confirmation: String) {
        viewModelScope.launch {
            registerUseCase(name, email, password, password_confirmation).collect {
                _user.emit(it)
            }
        }
    }

}