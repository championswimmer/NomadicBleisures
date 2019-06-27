package com.booking.nomadicbleisures.network

import com.booking.nomadicbleisures.network.interfaces.CombosApi
import com.booking.nomadicbleisures.network.interfaces.CoworkingApi
import com.booking.nomadicbleisures.network.interfaces.HotelsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val CITY_ID_MUMBAI = "-2092174"
    val CITY_ID_DENPASAR = "-2676772"


    private val interceptor = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    private val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://178.128.249.124:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    val hotelsApi = retrofit.create(HotelsApi::class.java)
    val coworkingApi = retrofit.create(CoworkingApi::class.java)
    val combosApi = retrofit.create(CombosApi::class.java)
}