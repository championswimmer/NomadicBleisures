package com.booking.nomadicbleisures.network

import com.booking.nomadicbleisures.network.interfaces.CoworkingApi
import com.booking.nomadicbleisures.network.interfaces.HotelsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val okHttpClient =
        OkHttpClient.Builder()
            .build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://distribution-xml.booking.com/2.4/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    val hotelsApi = retrofit.create(HotelsApi::class.java)
    val coworkingApi = retrofit.create(CoworkingApi::class.java)
}