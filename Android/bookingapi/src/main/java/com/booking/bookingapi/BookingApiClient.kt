package com.booking.bookingapi

import com.booking.bookingapi.interfaces.StaticAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookingApiClient {

    private val okHttpClient =
        OkHttpClient.Builder()
            .authenticator { route, response ->
                response.request().newBuilder()
                    .addHeader("Authorization", "Basic aGFja2F0b25fdGVhbV9hcm5hdjoyYmluMmJpbg==")
                    .build()
            }
            .build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://distribution-xml.booking.com/2.4/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    val staticAPI = retrofit.create(StaticAPI::class.java)
}