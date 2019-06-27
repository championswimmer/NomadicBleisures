package com.booking.nomadicbleisures.network

import com.booking.nomadicbleisures.network.interfaces.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient2 {

    private val interceptor = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    private val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://178.128.249.124:4242/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    val hotelsApi = retrofit.create(HotelsApi::class.java)
    val coworkingApi = retrofit.create(CoworkingApi::class.java)
    val combosApi = retrofit.create(CombosApi::class.java)
    val bleisuresApi = retrofit.create(BleisuresApi::class.java)
    val citiesApi = retrofit.create(CitiesApi::class.java)
}