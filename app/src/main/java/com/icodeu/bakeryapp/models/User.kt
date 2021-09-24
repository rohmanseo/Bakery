package com.icodeu.bakeryapp.models

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") var id: Int,
    @Json(name = "name") var name: String,
    @Json(name = "email") var email: String,
    @Json(name = "token") var token: String
)

data class SuccessResponse(
    @Json(name = "status") val status: String,
    @Json(name = "data") val user: User
)