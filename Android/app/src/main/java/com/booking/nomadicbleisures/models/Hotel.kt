package com.booking.nomadicbleisures.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hotel(@SerializedName("image") val image: String,
                 @SerializedName("name") val name: String,
                 @SerializedName("rating") val rating: Float,
                 @SerializedName("price") val price: String,
                 @SerializedName("currency") val currency: String,
                 @SerializedName("num_coworking") val numCoworking: Int): Parcelable