package com.booking.nomadicbleisures.network.interfaces

import com.booking.nomadicbleisures.models.Combo
import com.booking.nomadicbleisures.models.Coworking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CombosApi {

    @GET("combos")
    fun getCombos(@Query("city_ids") cityId: String): Call<List<Combo>>

}