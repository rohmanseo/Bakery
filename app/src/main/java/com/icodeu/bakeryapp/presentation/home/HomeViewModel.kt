package com.icodeu.bakeryapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.domain.model.Bread
import com.icodeu.bakeryapp.domain.use_case.bread.GetPopularBreadUseCase
import com.icodeu.bakeryapp.domain.use_case.bread.GetRecentBreadUseCase
import com.icodeu.bakeryapp.domain.use_case.user.LogoutUseCase
import com.icodeu.bakeryapp.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularBreadUseCase: GetPopularBreadUseCase,
    private val getRecentBreadUseCase: GetRecentBreadUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private var _logout = MutableSharedFlow<Resource<Boolean>>()
    val logout: SharedFlow<Resource<Boolean>>
        get() = _logout
    private val _popular = MutableSharedFlow<Resource<List<Bread>>>(1)
    val popular: SharedFlow<Resource<List<Bread>>>
        get() = _popular
    private val _recent = MutableSharedFlow<Resource<List<Bread>>>(1)
    val recent: SharedFlow<Resource<List<Bread>>>
        get() = _recent

    init {
        getPopular()
        getRecent()
    }

    fun getPopular() {
        viewModelScope.launch {
            getPopularBreadUseCase().collect {
                _popular.emit(it)
            }
        }
    }


    fun getRecent() {
        viewModelScope.launch {
            getRecentBreadUseCase().collect {
                _recent.emit(it)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().collect {
                _logout.emit(it)
            }
        }
    }

}