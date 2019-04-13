package com.booking.nomadicbleisures.models

import com.google.gson.annotations.SerializedName

data class NomadCity(@SerializedName("name") val name: String,
                     @SerializedName("country")val country: String,
                     @SerializedName("internet_speed")val internetSpeed: Int,
                     @SerializedName("air_quality_score") val airQualityScore: Int,
                     @SerializedName("temperatureC") val temperature: String,
                     @SerializedName("nomad_score") val nomadScore: Float)