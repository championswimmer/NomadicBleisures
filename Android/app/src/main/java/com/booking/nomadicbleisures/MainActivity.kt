package com.booking.nomadicbleisures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    companion object {
        val weatherMap = hashMapOf<String, String>().apply {
            put("wi-day-cloudy", "\uD83C\uDF25")
            put("wi-day-fog", "\uD83C\uDF2B")
            put("wi-day-sunny", "☀️")
            put("wi-day-rain", "\uD83C\uDF27")
            put("wi-snow-wind", "\uD83C\uDF28")
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, SearchFragment())
            commit()
        }
    }
}
