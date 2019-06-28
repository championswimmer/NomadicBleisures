package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Combo
import com.booking.nomadicbleisures.models.Coworking
import com.booking.nomadicbleisures.models.NomadCity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BleisuresApi {

    @GET("bleisures{queryString}")
    fun getBlesiures(@Path(value = "queryString") query: String): Call<List<NomadCity>>

}