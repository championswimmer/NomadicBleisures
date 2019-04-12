package com.booking.bookingapi.models

import com.booking.bookingapi.models.HotelData
import com.booking.bookingapi.models.RoomData
import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("hotel_data")
    var hotelData: HotelData,
    @SerializedName("hotel_id")
    var hotelId: Int,
    @SerializedName("room_data")
    var roomData: List<RoomData>
)