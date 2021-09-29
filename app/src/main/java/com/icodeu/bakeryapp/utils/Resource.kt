package com.icodeu.bakeryapp.utils

sealed class Resource<T>(
    val error: String? = "Unexpected error occurred",
    val data: T? = null
) {
    class Loading<T> : Resource<T>()
    class Idle<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(null, data)
    class Error<T>(message: String, data: T?=null) : Resource<T>(message, data)
}