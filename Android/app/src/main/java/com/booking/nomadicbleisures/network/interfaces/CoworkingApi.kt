package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Coworking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CoworkingApi {

    @GET("coworking-places")
    fun getCoworkingSpaces(@Query("city_ids") cityId: String): Call<List<Coworking>>
}