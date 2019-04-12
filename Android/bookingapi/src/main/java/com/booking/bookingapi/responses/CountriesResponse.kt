package com.booking.bookingapi.responses

import com.booking.bookingapi.models.Country
import com.booking.bookingapi.models.Meta
import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("meta")
    var meta: Meta,
    @SerializedName("result")
    var result: List<Country>
)