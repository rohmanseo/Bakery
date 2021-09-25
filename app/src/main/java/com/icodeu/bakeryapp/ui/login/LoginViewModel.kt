package com.icodeu.bakeryapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.models.ErrorResponse
import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.repositories.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(val userRepository: UserRepository) : ViewModel() {

    private var _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user
    private var _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn
    private var _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse>
        get() = _error
    private var _loading= MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading
    private val errorResponse = ErrorResponse(false)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _user.value = userRepository.login(email, password)
            }catch (e: Exception){
                _error.value = errorResponse.copy(
                    isError = true,
                    errorMessage = e.message.toString()
                )
            }
            _error.value = errorResponse.copy(isError = false)
            _loading.value = false
        }
    }

    fun isLoggedIn() {
        println("is logged in called")
        viewModelScope.launch {
            _loading.value = true
            try {
                _isLoggedIn.value = userRepository.isLoggedIn()
            }catch (e:Exception){
                _error.value = errorResponse.copy(isError = true, errorMessage = e.message)
            }
            _loading.value = false
            _error.value = errorResponse.copy(isError = false)
        }
    }
}