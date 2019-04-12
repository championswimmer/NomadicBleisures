package com.booking.bookingapi.interfaces

import com.booking.bookingapi.responses.CitiesResponse
import com.booking.bookingapi.responses.CountriesResponse
import com.booking.bookingapi.responses.HotelsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StaticAPI {

    @GET("hotels")
    fun getHotels(
        @Query("offset") offset: Int = 0,
        @Query("rows") rows: Int = 10,
        @Query("country_ids") countryIds: Array<String> = arrayOf(),
        @Query("city_ids") cityIds: Array<Int> = arrayOf()
    ): Call<HotelsResponse>

    @GET("cities")
    fun getCities(
        @Query("offset") offset: Int = 0,
        @Query("rows") rows: Int = 10,
        @Query("city_ids") cityIds: Array<Int> = arrayOf(),
        @Query("countries") countries: Array<String> = arrayOf()
    ): Call<CitiesResponse>

    @GET("countries")
    fun getCountries(
        @Query("offset") offset: Int = 0,
        @Query("rows") rows: Int = 10,
        @Query("countries") countries: Array<String> = arrayOf()
    ): Call<CountriesResponse>
}