package com.icodeu.bakeryapp.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.use_case.user.RegisterUseCase
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUseCase) :
    ViewModel() {

    private var _user = MutableLiveData<Resource<User>>(Resource.Idle())
    val user: LiveData<Resource<User>>
        get() = _user

    fun register(name: String, email: String, password: String, password_confirmation: String) {
        viewModelScope.launch {
            registerUseCase(name,email,password,password_confirmation).collect {
                _user.value = it
            }
        }
    }

}