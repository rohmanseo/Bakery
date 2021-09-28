package com.icodeu.bakeryapp.ui

import com.icodeu.bakeryapp.ui.ResponseStatus.STATUS_IDLE

data class ResponseData<T>(
    var status: String = STATUS_IDLE,
    var error: String? = null,
    var data: T? = null
)