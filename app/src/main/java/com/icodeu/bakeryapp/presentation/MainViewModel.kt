package com.icodeu.bakeryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.core.utils.Resource
import com.icodeu.bakeryapp.domain.model.User
import com.icodeu.bakeryapp.domain.use_case.main.ShowDialogUseCase
import com.icodeu.bakeryapp.domain.use_case.user.GetUserUseCase
import com.icodeu.bakeryapp.domain.use_case.user.IsLoggedInUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainViewModel(
    private val showDialogUseCase: ShowDialogUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val getUserUseCase: GetUserUseCase
    ) : ViewModel() {

    private val _showDialog = MutableSharedFlow<Resource<Boolean>>()
    val showDialog: SharedFlow<Resource<Boolean>>
        get() = _showDialog
    private val _isLoggedIn =
        MutableSharedFlow<Resource<Boolean>>()
    val isLoggedIn: SharedFlow<Resource<Boolean>>
        get() = _isLoggedIn
    private val _user =
        MutableSharedFlow<Resource<User?>>(
            1
        )
    val user: SharedFlow<Resource<User?>>
        get() = _user

    init {
        checkLoggedIn()
        getUser()
    }

    fun checkLoggedIn() = viewModelScope.launch {
        isLoggedInUseCase().collect {
            println("Main user changed to ${it.data}")
            _isLoggedIn.emit(it)
        }
    }

    fun getUser() = viewModelScope.launch {
        getUserUseCase().collect {
            println("Main user changed to ${it.data}")
            _user.emit(it)
        }

    }

    fun showDialog(isLoading: Boolean) = viewModelScope.launch {
        showDialogUseCase(isLoading).collect {
            _showDialog.emit(it)
        }
    }

}