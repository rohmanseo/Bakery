package com.icodeu.bakeryapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icodeu.bakeryapp.core.utils.Constants.BASE_URL
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "bread")
data class Bread(
    @Json(name = "id") @PrimaryKey val id: Int,
    @Json(name = "img") var img: String? = "",
    @Json(name = "name") var name: String? = "",
    @Json(name = "rating") var rating: Double? = 0.0,
    @Json(name = "price") var price: Double? = 0.0,
    @Json(name = "views") var views: Int? = 0
):Parcelable {
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