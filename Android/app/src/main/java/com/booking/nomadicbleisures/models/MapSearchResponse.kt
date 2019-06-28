package com.booking.nomadicbleisures.models

import com.google.gson.annotations.SerializedName

/**
 * Created by prempal on 2019-06-28.
 */
class MapSearchResponse {

    @SerializedName("hotels")
    val hotels: List<Hotel>? = null
    @SerializedName("coworking")
    val coworkingPlaces: List<Coworking>? = null
}