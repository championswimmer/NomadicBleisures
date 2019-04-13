package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Hotel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HotelsApi {

    @GET("hotels")
    fun getHotels(@Query("city_ids") cityId: String): Call<List<Hotel>>
}