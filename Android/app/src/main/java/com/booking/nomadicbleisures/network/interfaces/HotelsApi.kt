package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Hotel
import retrofit2.Call
import retrofit2.http.GET

interface HotelsApi {

    @GET("coworking-hotels")
    fun getHotels(): Call<List<Hotel>>
}