package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Combo
import com.booking.nomadicbleisures.models.Coworking
import com.booking.nomadicbleisures.models.NomadCity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface BleisuresApi {

    @GET
    fun getBlesiures(@Url url: String): Call<List<NomadCity>>

}