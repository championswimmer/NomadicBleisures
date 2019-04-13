package com.booking.nomadicbleisures

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.booking.nomadicbleisures.models.NomadCity
import com.booking.nomadicbleisures.utils.IOUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.item_city.view.*


class SearchFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        view.progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            fetchPopularCities()
            view.progressBar.visibility = View.GONE
        }, 1000)

        return view
    }

    private fun fetchPopularCities() {
        val listType = object : TypeToken<List<NomadCity>>() {}.type
        val cityList = Gson().fromJson(IOUtils.loadJSONFromAsset(activity!!, "cities.json"), listType)
                as List<NomadCity>
        for (city in cityList) {
            val cityView = LayoutInflater.from(activity!!).inflate(R.layout.item_city, llCityList, false)
            cityView.cityTitle.text = city.name
            cityView.cityPrice.text = "$${city.price}"
            cityView.cityWeather.text = "${city.temperature}C"
            cityView.cityInternet.text = "${city.internetSpeed.toShort()}mbps"
            Picasso.get().load("https://nomadlist.com${city.image}").into(cityView.cityImage);
            llCityList.addView(cityView)
        }
    }
}