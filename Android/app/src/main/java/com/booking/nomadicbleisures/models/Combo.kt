package com.booking.nomadicbleisures.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Combo(@SerializedName("hotel") val hotel: Hotel?,
                 @SerializedName("coworking") val coworking: Coworking?): Parcelable