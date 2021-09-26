package com.icodeu.bakeryapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.models.ErrorResponse
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading
    protected val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse>
        get() = _error
    protected var errorResponse = ErrorResponse(false)

    fun performBackgroundTask(task: suspend () -> Unit) {
        viewModelScope.launch {
            _loading.value = true
            try {
                task()
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = errorResponse.copy(isError = true, e.message)
            }
            _error.value = errorResponse.copy(isError = false)
            _loading.value = false
        }
    }

}