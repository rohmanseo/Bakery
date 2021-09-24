package com.icodeu.bakeryapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.repositories.UserRepository
import com.icodeu.bakeryapp.utils.UIState
import kotlinx.coroutines.launch

class LoginViewModel(val userRepository: UserRepository) : ViewModel() {

    private var _user = MutableLiveData<LoginState>(LoginState(UIState.STATE_IDLE))
    val user: LiveData<LoginState>
        get() = _user
    private var _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _user.value = userRepository.login(email, password)
        }
    }

    fun isLoggedIn() {
        viewModelScope.launch {
            _isLoggedIn.value = userRepository.isLoggedIn()
        }
    }
}