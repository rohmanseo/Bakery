package com.icodeu.bakeryapp.utils

import com.icodeu.bakeryapp.presentation.ResponseStatus.STATUS_ERROR
import com.icodeu.bakeryapp.presentation.ResponseStatus.STATUS_IDLE
import com.icodeu.bakeryapp.presentation.ResponseStatus.STATUS_LOADING
import com.icodeu.bakeryapp.presentation.ResponseStatus.STATUS_SUCCESS

sealed class Resource<T>(
    val status: String = STATUS_IDLE,
    val error: String? = "Unexpected error occurred",
    val data: T? = null
){
    class Loading<T>: Resource<T>(STATUS_LOADING)
    class Idle<T>: Resource<T>(STATUS_IDLE)
    class Success<T>(data:T):Resource<T>(STATUS_SUCCESS,null,data)
    class Error<T>(message:String):Resource<T>(STATUS_ERROR,message)
}