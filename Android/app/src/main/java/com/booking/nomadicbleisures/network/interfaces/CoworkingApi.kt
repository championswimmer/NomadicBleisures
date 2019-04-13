package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Coworking
import retrofit2.Call
import retrofit2.http.GET

interface CoworkingApi {

    @GET("coworking-spaces")
    fun getCoworkingSpaces(): Call<List<Coworking>>
}