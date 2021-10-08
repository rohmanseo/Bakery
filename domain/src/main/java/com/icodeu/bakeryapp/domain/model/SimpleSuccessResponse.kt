package com.icodeu.bakeryapp.domain.model

import com.squareup.moshi.Json

data class SimpleSuccessResponse(
    @Json(name = "status") val status:String
) {
}