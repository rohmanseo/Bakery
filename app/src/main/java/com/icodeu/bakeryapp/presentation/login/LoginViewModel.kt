package com.icodeu.bakeryapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.data.repository.UserRepository
import com.icodeu.bakeryapp.ui.ResponseData
import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_ERROR
import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_LOADING
import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_SUCCESS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(val userRepository: UserRepository) : ViewModel() {

    private var _user = MutableLiveData<ResponseData<User>>(ResponseData())
    val user: LiveData<ResponseData<User>>
        get() = _user
    private var _isLoggedIn = MutableLiveData<ResponseData<Boolean>>(ResponseData())
    val isLoggedIn: LiveData<ResponseData<Boolean>>
        get() = _isLoggedIn

    init {
        isLoggedIn()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _user.value = _user.value?.copy(STATUS_LOADING, null, null)
            try {
                val user = userRepository.login(email, password)
                _user.value = _user.value?.copy(STATUS_SUCCESS, null, user)
            } catch (e: Exception) {
                _user.value = _user.value?.copy(STATUS_ERROR, e.message, null)
            }
        }
    }

    fun isLoggedIn() {
        viewModelScope.launch {
            _isLoggedIn.value = _isLoggedIn.value?.copy(STATUS_LOADING, null, null)
            try {
                userRepository.isLoggedIn()
                    .collect {
                        _isLoggedIn.value = _isLoggedIn.value?.copy(STATUS_SUCCESS, null, it)
                    }
            } catch (e: Exception) {
                _isLoggedIn.value = _isLoggedIn.value?.copy(STATUS_ERROR, e.message, null)
            }
        }
    }

}