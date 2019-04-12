package com.booking.bookingapi.responses

import com.booking.bookingapi.models.City
import com.booking.bookingapi.models.Meta
import com.google.gson.annotations.SerializedName

data class CitiesResponse(
    @SerializedName("meta")
    var meta: Meta,
    @SerializedName("result")
    var result: List<City>
)