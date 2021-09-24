package com.icodeu.bakeryapp.ui.login

import com.icodeu.bakeryapp.models.SuccessResponse
import com.icodeu.bakeryapp.utils.UIState

data class LoginState(
    var state: String = UIState.STATE_IDLE,
    var errorMessage: String? = null,
    var data: SuccessResponse? = null
) {
}