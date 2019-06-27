package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Combo
import com.booking.nomadicbleisures.models.Coworking
import com.booking.nomadicbleisures.models.NomadCity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BleisuresApi {

    @GET("bleisures")
    fun getBlesiures(): Call<List<NomadCity>>

}