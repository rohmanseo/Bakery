package com.icodeu.bakeryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.models.Bread
import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.repositories.BreadRepository
import com.icodeu.bakeryapp.repositories.UserRepository
import com.icodeu.bakeryapp.ui.ResponseData
import com.icodeu.bakeryapp.ui.ResponseStatus
import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_LOADING
import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_SUCCESS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val breadRepository: BreadRepository
) : ViewModel() {

    private var _user = MutableLiveData<ResponseData<User>>(ResponseData())
    val user: LiveData<ResponseData<User>>
        get() = _user
    private val _popular = MutableLiveData<ResponseData<List<Bread>>>(ResponseData())
    val popular: LiveData<ResponseData<List<Bread>>>
        get() = _popular
    private val _recent = MutableLiveData<ResponseData<List<Bread>>>(ResponseData())
    val recent: LiveData<ResponseData<List<Bread>>>
        get() = _recent


    fun getPopular() {
        viewModelScope.launch {
            _popular.value = _popular.value?.copy(STATUS_LOADING)
            try {
                breadRepository.getPopular().collect {
                    _popular.value = _popular.value?.copy(STATUS_SUCCESS, null, it)
                }
            } catch (e: Exception) {
                _popular.value = _popular.value?.copy(ResponseStatus.STATUS_ERROR, e.message)
            }
        }
    }


    fun getRecent() {
        viewModelScope.launch {
            _recent.value = _recent.value?.copy(STATUS_LOADING)
            try {
                breadRepository.getRecent().collect {
                    _recent.value = _recent.value?.copy(STATUS_SUCCESS, null, it)
                }
            } catch (e: Exception) {
                _recent.value = _recent.value?.copy(ResponseStatus.STATUS_ERROR, e.message)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _user.value = _user.value?.copy(ResponseStatus.STATUS_LOADING, null, null)
            try {
                userRepository.logout()
                _user.value = _user.value?.copy(STATUS_SUCCESS, null, null)
            } catch (e: Exception) {
                _user.value = _user.value?.copy(ResponseStatus.STATUS_ERROR, e.message, null)
            }
        }
    }

    fun getLoggedInUser() {
        viewModelScope.launch {
            _user.value = _user.value?.copy(ResponseStatus.STATUS_LOADING, null, null)
            try {
                userRepository.getLoggedInUser().collect {
                    _user.value = _user.value?.copy(STATUS_SUCCESS, null, it)
                }
            } catch (e: Exception) {
                _user.value = _user.value?.copy(ResponseStatus.STATUS_ERROR, e.message, null)
            }
        }
    }

}