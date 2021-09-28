package com.icodeu.bakeryapp.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.repositories.UserRepository
import com.icodeu.bakeryapp.ui.ResponseData
import com.icodeu.bakeryapp.ui.ResponseStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _isLoggedIn =
        MutableLiveData<ResponseData<Boolean>>(ResponseData(ResponseStatus.STATUS_IDLE, null, null))
    val isLoggedIn: LiveData<ResponseData<Boolean>>
        get() = _isLoggedIn

    init {
        getUser()
    }

    fun checkLoggedIn() {
        viewModelScope.launch {
            try {
                userRepository.isLoggedIn().collect {
                    _isLoggedIn.value =
                        _isLoggedIn.value?.copy(ResponseStatus.STATUS_SUCCESS, null, it)
                }
            } catch (e: Exception) {
                _isLoggedIn.value =
                    _isLoggedIn.value?.copy(ResponseStatus.STATUS_ERROR, e.message, null)
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            userRepository.getLoggedInUser().collect {
                println("Logged in user : ${it}")
            }
        }
    }

}