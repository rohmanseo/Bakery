package com.icodeu.bakeryapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.repositories.UserRepository
import com.icodeu.bakeryapp.ui.BaseViewModel
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) :
    BaseViewModel<UserRepository>(userRepository) {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun register(name: String, email: String, password: String, password_confirmation: String){
        viewModelScope.launch {
            _loading.value = true
            try {
                _user.value = userRepository.register(
                    name = name,
                    email = email,
                    password = password,
                    password_confirmation = password_confirmation
                )
            }catch (e:Exception){
                _error.value = errorResponse.copy(isError = true, errorMessage = e.message)
            }
            _loading.value = false
            _error.value = errorResponse.copy(isError = false)
        }
    }
}