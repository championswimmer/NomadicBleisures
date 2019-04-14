package com.booking.nomadicbleisures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.booking.nomadicbleisures.models.Combo
import com.booking.nomadicbleisures.models.NomadCity
import com.booking.nomadicbleisures.network.ApiClient
import com.booking.nomadicbleisures.utils.IOUtils
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.card_city_details.*
import kotlinx.android.synthetic.main.content_city.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityActivity : AppCompatActivity() {

    private lateinit var cityId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Wooooshhhh....", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        toolbar_layout.setCollapsedTitleTypeface(
            ResourcesCompat.getFont(this, R.font.signika_negative_semibold)
        )
        toolbar_layout.setExpandedTitleTypeface(
            ResourcesCompat.getFont(this, R.font.signika_negative)
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent!!.getParcelableExtra<NomadCity>("detail").apply {
            this@CityActivity.cityId = cityId
            setSupportActionBar(toolbar.apply {
                title = "$name $weatherEmoji $temperature°C"
            })
            Picasso.get().load("https://nomadlist.com/$image").into(image_header)
            tv_aqi.text = String.format("%.2f", airQualityScore)
            tv_safety.text = safetyLevel.toString()
            tv_tempC.text = "$temperatureFeels°C"
        }

        ApiClient.combosApi.getCombos(cityId).enqueue(object : Callback<List<Combo>> {
            override fun onResponse(call: Call<List<Combo>>, response: Response<List<Combo>>) {
                response.body()?.let {
                    showCombos(it)
                }
            }

            override fun onFailure(call: Call<List<Combo>>, t: Throwable) {}
        })

    }

    private fun showCombos(combos: List<Combo>) {
        progressBar.visibility = View.GONE
        for (combo in combos) {
            val comboView = ComboView(this, null)
            comboView.setup(combo.coworking, combo.hotel)
            llComboList.addView(comboView)
        }
    }
}
