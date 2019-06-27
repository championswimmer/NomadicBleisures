package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Filters
import com.booking.nomadicbleisures.models.Hotel
import com.booking.nomadicbleisures.models.NomadCity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MetaApi {

    @GET("meta/selections")
    fun getFilters(): Call<Filters>

}