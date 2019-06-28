package com.booking.nomadicbleisures.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by prempal on 2019-06-28.
 */
@Parcelize
class Location : Parcelable {
    @SerializedName("latitude")
    val latitude: Double = 0.0
    @SerializedName("longitude")
    val longitude: Double = 0.0
}