package com.booking.nomadicbleisures.utils

import android.content.Context
import com.booking.nomadicbleisures.models.NomadCity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object IOUtils {

    fun loadJSONFromAsset(context: Context, fileName: String): String? {
        var json: String? = null
        try {
            val `is` = context.getAssets().open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    fun loadCities(context: Context): List<NomadCity> {
        val listType = object : TypeToken<List<NomadCity>>() {}.type
        val cityList = Gson().fromJson(IOUtils.loadJSONFromAsset(context, "cities.json"), listType)
                as List<NomadCity>
        return cityList
    }
}