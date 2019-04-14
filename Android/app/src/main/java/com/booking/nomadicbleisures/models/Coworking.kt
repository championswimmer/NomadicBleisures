package com.booking.nomadicbleisures.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coworking(@SerializedName("name") val name: String,
                     @SerializedName("m_price") val monthlyPrice: String,
                     @SerializedName("num_hotels") val numHotels: Int,
                     @SerializedName("attractions") val attractions: String,
                     @SerializedName("image") val image: String,
                     @SerializedName("currency") val currency: String,
                     @SerializedName("rating") val rating: Float,
                     @SerializedName("recommended_hotel") val recommendedHotel: Hotel): Parcelable
