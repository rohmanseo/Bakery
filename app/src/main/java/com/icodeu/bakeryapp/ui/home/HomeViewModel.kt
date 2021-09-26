package com.icodeu.bakeryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.models.Bread
import com.icodeu.bakeryapp.models.User
import com.icodeu.bakeryapp.repositories.BreadRepository
import com.icodeu.bakeryapp.repositories.UserRepository
import com.icodeu.bakeryapp.ui.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val breadRepository: BreadRepository
) : BaseViewModel() {

    private val _user: MutableLiveData<User?> = MutableLiveData()
    val user: LiveData<User?>
        get() = _user
    private val _popular = MutableLiveData<List<Bread>>()
    val popular: LiveData<List<Bread>>
        get() = _popular
    private val _recent = MutableLiveData<List<Bread>>()
    val recent: LiveData<List<Bread>>
        get() = _popular

    fun getPopular() {
        performBackgroundTask {
            _popular.value = breadRepository.getPopular()
        }
    }

    fun getRecent(){
        performBackgroundTask {
            _recent.value = breadRepository.getRecent()
        }
    }

    fun logout() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val isSuccess = userRepository.logout()
                if (isSuccess) {
                    _user.value = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = errorResponse.copy(isError = true, e.message)
            }
            _error.value = errorResponse.copy(isError = false)
            _loading.value = false
        }
    }

    fun getLoggedInUser() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _user.value = userRepository.getLoggedInUser()
            } catch (e: Exception) {
                _error.value = errorResponse.copy(isError = true, e.message)
            }
            _error.value = errorResponse.copy(isError = false)
            _loading.value = false
        }
    }

}