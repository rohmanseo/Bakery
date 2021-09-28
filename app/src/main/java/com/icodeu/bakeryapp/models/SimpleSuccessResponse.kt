package com.icodeu.bakeryapp.models

import com.squareup.moshi.Json

data class SimpleSuccessResponse(@Json(name = "status") val status: String) {
}