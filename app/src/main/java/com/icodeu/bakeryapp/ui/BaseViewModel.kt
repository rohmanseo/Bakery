package com.icodeu.bakeryapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icodeu.bakeryapp.models.ErrorResponse
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading
    protected val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse>
        get() = _error
    protected var errorResponse = ErrorResponse(false)
    protected abstract var taskCount: Int


    init {
        _loading.value = true
        initLoadingFlow()
    }

    private fun initLoadingFlow() {
        viewModelScope.launch {
            emitTaskCompleted()
                .onStart {
                    if (taskCount == 0){
                        _loading.value = false
                    }
                }
                .filter { taskCount == 0 }
                .collect {
                    _loading.value = false
                }
        }
    }

    protected fun emitTaskCompleted(): Flow<Int> = flow {
        emit(taskCount--)
    }

    fun performViewmodelTask(task: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                task()
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = errorResponse.copy(isError = true, e.message)
            }
        }
    }

}