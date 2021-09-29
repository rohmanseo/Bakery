package com.icodeu.bakeryapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.use_case.user.IsLoggedInUseCase
import com.icodeu.bakeryapp.domain.use_case.user.LoginUseCase
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val loginUserUseCase: LoginUseCase
) : ViewModel() {

    private var _user = MutableLiveData<Resource<User>>(Resource.Idle())
    val user: LiveData<Resource<User>>
        get() = _user
    private val _isLoggedIn =
        MutableLiveData<Resource<Boolean>>(Resource.Idle())
    val isLoggedIn: LiveData<Resource<Boolean>>
        get() = _isLoggedIn

    init {
        isLoggedIn()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUserUseCase(email, password).collect {
                _user.value = it
            }
        }
    }

    fun isLoggedIn() {
        viewModelScope.launch {
            isLoggedInUseCase().collect {
                _isLoggedIn.value = it
            }
        }
    }

}