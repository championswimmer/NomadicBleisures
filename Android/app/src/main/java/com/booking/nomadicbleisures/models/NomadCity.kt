package com.booking.nomadicbleisures.models

import com.google.gson.annotations.SerializedName

data class NomadCity(@SerializedName("name") val name: String,
                     @SerializedName("country")val country: String,
                     @SerializedName("internet_speed")val internetSpeed: Int,
                     @SerializedName("air_quality_score") val airQualityScore: Float,
                     @SerializedName("weather_emoji") val weatherEmoji: String,
                     @SerializedName("temperatureC") val temperature: String,
                     @SerializedName("nomad_score") val nomadScore: Float,
                     @SerializedName("short_term_cost_in_usd") val price: Int,
                     @SerializedName("image") val image: String)