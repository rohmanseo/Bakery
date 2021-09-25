package com.icodeu.bakeryapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icodeu.bakeryapp.models.ErrorResponse

abstract class BaseViewModel: ViewModel() {

    protected val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading
    protected val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse>
        get() = _error
    protected var errorResponse = ErrorResponse(false)

}