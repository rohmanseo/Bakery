package com.icodeu.bakeryapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.repositories.UserRepository
import com.icodeu.bakeryapp.ui.BaseViewModel
import com.icodeu.bakeryapp.ui.ResponseData
import com.icodeu.bakeryapp.ui.ResponseStatus
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) :
    ViewModel() {

    private var _user = MutableLiveData<ResponseData<User>>(ResponseData())
    val user: LiveData<ResponseData<User>>
        get() = _user

    fun register(name: String, email: String, password: String, password_confirmation: String) {
        viewModelScope.launch {
            try {
                _user.value = _user.value?.copy(ResponseStatus.STATUS_SUCCESS,null,userRepository.register(
                    name = name,
                    email = email,
                    password = password,
                    password_confirmation = password_confirmation
                ))
            }catch (e:Exception){
                _user.value = _user.value?.copy(ResponseStatus.STATUS_ERROR, e.message, null)
            }
        }
    }

}