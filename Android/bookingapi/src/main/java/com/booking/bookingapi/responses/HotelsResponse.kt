package com.booking.bookingapi.responses

import com.booking.bookingapi.models.Hotel
import com.booking.bookingapi.models.Meta
import com.google.gson.annotations.SerializedName

data class HotelsResponse(
    @SerializedName("meta")
    var meta: Meta,
    @SerializedName("result")
    var result: List<Hotel>
)