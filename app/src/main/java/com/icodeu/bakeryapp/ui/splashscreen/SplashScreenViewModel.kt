package com.icodeu.bakeryapp.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.repositories.UserRepository
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()

    fun checkLoggedIn() {
        viewModelScope.launch {
            _isLoggedIn.value = userRepository.isLoggedIn()
        }
    }

    fun isLoggedIn(): LiveData<Boolean> = _isLoggedIn
}