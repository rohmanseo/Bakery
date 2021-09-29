package com.icodeu.bakeryapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.use_case.bread.GetPopularBreadUseCase
import com.icodeu.bakeryapp.domain.use_case.bread.GetRecentBreadUseCase
import com.icodeu.bakeryapp.domain.use_case.user.GetUserUseCase
import com.icodeu.bakeryapp.domain.use_case.user.LogoutUseCase
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getLoggedInUseCase: GetUserUseCase,
    private val getPopularBreadUseCase: GetPopularBreadUseCase,
    private val getRecentBreadUseCase: GetRecentBreadUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private var _user = MutableLiveData<Resource<User>>(Resource.Idle())
    val user: LiveData<Resource<User>>
        get() = _user
    private var _logout = MutableLiveData<Resource<Boolean>>(Resource.Idle())
    val logout: LiveData<Resource<Boolean>>
        get() = _logout
    private val _popular = MutableLiveData<Resource<List<Bread>>>(Resource.Idle())
    val popular: LiveData<Resource<List<Bread>>>
        get() = _popular
    private val _recent = MutableLiveData<Resource<List<Bread>>>(Resource.Idle())
    val recent: LiveData<Resource<List<Bread>>>
        get() = _recent

    init {
        getLoggedInUser()
    }

    fun getPopular() {
        viewModelScope.launch {
            getPopularBreadUseCase().collect {
                _popular.value = it
            }
        }
    }


    fun getRecent() {
        viewModelScope.launch {
            getRecentBreadUseCase().collect {
                _recent.value = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().collect {
                _logout.value = it
            }
        }
    }

    fun getLoggedInUser() {
        viewModelScope.launch {
            getLoggedInUseCase().collect {
                _user.value = it
            }

        }

    }
}