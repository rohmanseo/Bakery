package com.icodeu.bakeryapp.models

import com.squareup.moshi.Json

data class LogoutResponse(@Json(name = "status") val status: String) {
}