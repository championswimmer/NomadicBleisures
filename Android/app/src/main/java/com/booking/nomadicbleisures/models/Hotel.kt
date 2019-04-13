package com.booking.nomadicbleisures.models

import com.google.gson.annotations.SerializedName

data class Hotel(@SerializedName("image") val image: String,
                 @SerializedName("name") val name: String,
                 @SerializedName("rating") val rating: Float,
                 @SerializedName("price") val price: String,
                 @SerializedName("num_coworking") val numCoworking: Int)