package com.booking.bookingapi

import com.booking.bookingapi.interfaces.StaticAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookingApiClient {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://distribution-xml.booking.com/2.4/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val staticAPI = retrofit.create(StaticAPI::class.java)
}