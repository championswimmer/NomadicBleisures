package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.MapSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by prempal on 2019-06-28.
 */
interface MapSearchApi {

    @GET("map-search")
    fun getMapSearchResults(
        @Query("lat") lat: Double,
        @Query("lng") lon: Double,
        @Query("radius") radius: Float
    ): Call<MapSearchResponse>
}