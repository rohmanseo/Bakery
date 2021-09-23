package com.icodeu.bakeryapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.models.SuccessResponse
import com.icodeu.bakeryapp.network.LoginApi
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var _user = MutableLiveData<LiveData<SuccessResponse>>()
    var user: LiveData<SuccessResponse>? = _user.value

    fun login(email: String, password: String): LiveData<SuccessResponse>? {
        viewModelScope.launch {
            LoginApi.retrofitService.login(email, password).let {
                _user.postValue(it)
            }
        }
        return user
    }
}