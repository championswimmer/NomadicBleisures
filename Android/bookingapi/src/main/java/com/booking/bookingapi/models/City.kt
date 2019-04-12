package com.booking.bookingapi.models

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("city_id")
    var cityId: Int,
    @SerializedName("country")
    var country: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("nr_hotels")
    var nrHotels: Int,
    @SerializedName("translations")
    var translations: List<Translation>
)