package com.booking.nomadicbleisures.models

import com.google.gson.annotations.SerializedName

data class Coworking(@SerializedName("name") val name: String,
                     @SerializedName("m_price") val monthlyPrice: String,
                     @SerializedName("num_hotels") val numHotels: Int,
                     @SerializedName("attractions") val attractions: String,
                     @SerializedName("image") val image: String,
                     @SerializedName("currency") val currency: String)
