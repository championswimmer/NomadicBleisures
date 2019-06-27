package com.booking.nomadicbleisures.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingCity(@SerializedName("id") val id: String,
                       @SerializedName("url") val url: String,
                       @SerializedName("label") val label: String,
                       @SerializedName("cityId") val cityId: Long): Parcelable