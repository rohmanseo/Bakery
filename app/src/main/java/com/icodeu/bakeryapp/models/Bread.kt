package com.icodeu.bakeryapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icodeu.bakeryapp.utils.Constant.BASE_URL
import com.squareup.moshi.Json

@Entity(tableName = "bread")
data class Bread(
    @Json(name = "id") @PrimaryKey val id: Int,
    @Json(name = "img") var img: String? = "",
    @Json(name = "name") var name: String? = "",
    @Json(name = "rating") var rating: Double? = 0.0,
    @Json(name = "price") var price: Double? = 0.0,
    @Json(name = "views") var views: Int? = 0
) {
    fun shortPrice(): Int {
        return (this.price?.div(1000.0))?.toInt() ?: 0
    }

    fun validImage(): String {
        return BASE_URL + this.img
    }
}

data class BreadResponse(
    @Json(name = "status") val status: String,
    @Json(name = "data") val breads: List<Bread>
)