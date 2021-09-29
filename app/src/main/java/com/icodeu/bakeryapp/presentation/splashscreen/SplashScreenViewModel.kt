package com.icodeu.bakeryapp.presentation.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.domain.use_case.user.IsLoggedInUseCase
import com.icodeu.bakeryapp.domain.use_case.user.GetUserUseCase
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _isLoggedIn =
        MutableLiveData<Resource<Boolean>>(Resource.Idle())
    val isLoggedIn: LiveData<Resource<Boolean>>
        get() = _isLoggedIn

    init {
        checkLoggedIn()
        getUser()
    }

    fun checkLoggedIn() {
        viewModelScope.launch {
            isLoggedInUseCase().collect {
                _isLoggedIn.value = it
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            getUserUseCase().collect {
                println("User is ${it}")
            }
        }
    }

}