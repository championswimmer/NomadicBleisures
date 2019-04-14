package com.booking.nomadicbleisures.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NomadCity(@SerializedName("name") val name: String,
                     @SerializedName("country")val country: String,
                     @SerializedName("internet_speed")val internetSpeed: Int,
                     @SerializedName("air_quality_score") val airQualityScore: Float,
                     @SerializedName("weather_emoji") val weatherEmoji: String,
                     @SerializedName("temperatureC") val temperature: String,
                     @SerializedName("temperatureC_feels_like") val temperatureFeels: String,
                     @SerializedName("nomad_score") val nomadScore: Float,
                     @SerializedName("safety_level") val safetyLevel: Float,
                     @SerializedName("short_term_cost_in_usd") val price: Int,
                     @SerializedName("image") val image: String,
                     @SerializedName("city_id") val cityId: String): Parcelable