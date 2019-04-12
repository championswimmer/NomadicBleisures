package com.booking.bookingapi.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("area")
    var area: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("translations")
    var translations: List<Translation>
)