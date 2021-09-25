package com.icodeu.bakeryapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "user")
data class User(
    @Json(name = "id") @PrimaryKey var id: Int,
    @Json(name = "name") var name: String,
    @Json(name = "email") var email: String,
    @Json(name = "token") var token: String=""
)

data class UserResponse(
    @Json(name = "status") val status: String,
    @Json(name = "data") val user: User
)