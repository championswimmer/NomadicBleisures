package com.booking.nomadicbleisures.models

import com.google.gson.annotations.SerializedName

data class Combo(@SerializedName("hotel") val hotel: Hotel,
                 @SerializedName("coworking") val coworking: Coworking)