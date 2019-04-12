package com.booking.bookingapi.models

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("language")
    var language: String,
    @SerializedName("name")
    var name: String
)