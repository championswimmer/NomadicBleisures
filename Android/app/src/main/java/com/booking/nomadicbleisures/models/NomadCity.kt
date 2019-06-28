package com.booking.nomadicbleisures.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NomadCity(@SerializedName("name") val name: String,
                     @SerializedName("country")val country: Country,
                     @SerializedName("internetMbps")val internetSpeed: Int,
                     @SerializedName("aqi") val airQualityScore: Float,
                     @SerializedName("weatherEmoji") val weatherEmoji: String,
                     @SerializedName("tempC") val temperature: String,
                     @SerializedName("tempCfeels") val temperatureFeels: String,
                     @SerializedName("score") val nomadScore: Float,
                     @SerializedName("safetyLevel") val safetyLevel: Float,
                     @SerializedName("livingCost") val price: Int,
                     @SerializedName("image") val image: String,
                     @SerializedName("combos") var combos: List<Combo>? = arrayListOf(),
                     @SerializedName("recommended") val recommended: Boolean,
                     @SerializedName("booking_cities") val bookingCities: List<BookingCity>? = arrayListOf()): Parcelable {

    @Parcelize
    data class Country(@SerializedName("name") val name: String): Parcelable

}