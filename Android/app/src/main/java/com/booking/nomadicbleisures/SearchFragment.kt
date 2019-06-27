package com.booking.nomadicbleisures

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.booking.nomadicbleisures.models.NomadCity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item_city.view.*
import android.view.animation.AnimationUtils
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentTransaction
import com.booking.nomadicbleisures.network.ApiClient2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    lateinit var rootView: View

    var flag = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_search, container, false)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slide_up = AnimationUtils.loadAnimation(
            activity!!,
            com.booking.nomadicbleisures.R.anim.slide_up
        )
        Handler().postDelayed({
            title.startAnimation(slide_up)
        }, 100)

        progressBar.visibility = View.VISIBLE
        fetchPopularCities()

        et_destination.threshold = 3

        et_destination.addTextChangedListener {
            it?.let { query ->
                if (query.toString().length >= 2) {
                    ApiClient2.citiesApi.getCities(name = query.toString()).enqueue(object : Callback<List<NomadCity>> {
                        override fun onResponse(call: Call<List<NomadCity>>, response: Response<List<NomadCity>>) {
                            response.body()?.let {
                                showCityAutocomplete(it)

                            }
                        }

                        override fun onFailure(call: Call<List<NomadCity>>, t: Throwable) {}
                    })
                }
            }
        }

        et_dates.setOnClickListener {
            et_dates.text = null
            DatePickerDialog(context, this, 2019, 3, 14).show()
        }

        btn_search.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                add(
                    R.id.container, ListingsFragment.newInstance(
                        if (toggle_switch.isChecked) ListingsFragment.Companion.SearchType.COWORKING
                        else ListingsFragment.Companion.SearchType.HOTELS
                    )
                )
                addToBackStack(null)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                commit()
            }
        }

        btn_build.setOnClickListener {
            BuildBleisureFragment().show(childFragmentManager, "Build")
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (flag == 0) {
            DatePickerDialog(context, this, 2019, 3, 15).show()
            flag = 1
        } else {
            flag = 0
            et_dates.setText("14 Apr - 13 May")
        }
    }


    private fun showCityAutocomplete(cityList: List<NomadCity>) {
        val destinations = arrayListOf<String>()
        for (city in cityList) {
            if (city.bookingCities == null) continue
            for (c in city.bookingCities) {
                destinations.add(c.label)
            }
        }
        val adapter = ArrayAdapter<String>(context, android.R.layout.select_dialog_item, destinations)
        et_destination.setAdapter(adapter)
    }

    private fun fetchPopularCities() {

        ApiClient2.citiesApi.getCities(limit = "5").enqueue(object : Callback<List<NomadCity>> {
            override fun onResponse(call: Call<List<NomadCity>>, response: Response<List<NomadCity>>) {
                response.body()?.let {
                    showCities(it)
                    progressBar.visibility = View.GONE
                    llCityList.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<NomadCity>>, t: Throwable) {}
        })
    }

    private fun showCities(cityList: List<NomadCity>) {
        for (city in cityList) {
            val cityView = LayoutInflater.from(activity!!).inflate(R.layout.item_city, llCityList, false)
            cityView.cityTitle.text = city.name
            cityView.cityPrice.text = "$${city.price}"
            cityView.cityWeather.text = "${city.temperature}C"
            cityView.cityInternet.text = "${city.internetSpeed}mbps"
            cityView.cityNomadScore.text = "${Math.round(city.nomadScore * 10.0) / 10.0}"
            Picasso.get().load(city.image).into(cityView.cityImage);
            cityView.setOnClickListener {
                startActivity(Intent(context, CityActivity::class.java).apply { putExtra("detail", city) })
            }
            llCityList.addView(cityView)
        }

    }
}