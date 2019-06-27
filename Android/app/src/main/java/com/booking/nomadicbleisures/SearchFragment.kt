package com.booking.nomadicbleisures

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.booking.nomadicbleisures.models.NomadCity
import com.booking.nomadicbleisures.utils.IOUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.item_city.view.*
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import androidx.fragment.app.FragmentTransaction

class SearchFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    val destinations = arrayOf<String>(
        "Mumbai, Maharashtra, India",
        "Chhatrapati Shivaji International Airport Mumbai, Mumbai, Maharashtra, India",
        "Mumbai Central, Mumbai, Maharashtra, India",
        "South Mumbai, Mumbai, Maharashtra, India",
        "Navi Mumbai, Mumbai, Maharashtra, India"
    )

    var flag = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val slide_up = AnimationUtils.loadAnimation(
            activity!!,
            com.booking.nomadicbleisures.R.anim.slide_up
        )
        Handler().postDelayed({
            view.title.startAnimation(slide_up)
        }, 100)

        view.progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            fetchPopularCities()
            view.progressBar.visibility = View.GONE
            view.llCityList.visibility = View.VISIBLE
        }, 1000)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_destination.threshold = 3
        val adapter = ArrayAdapter<String>(context, android.R.layout.select_dialog_item, destinations)
        et_destination.setAdapter(adapter)

        et_dates.setOnClickListener {
            et_dates.text = null
            DatePickerDialog(context, this, 2019, 3, 13).show()
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
            DatePickerDialog(context, this, 2019, 3, 14).show()
            flag = 1
        } else {
            flag = 0
            et_dates.setText("13 Apr - 25 May")
        }
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
            cityView.cityInternet.text = "${city.internetSpeed}mbps"
            cityView.cityNomadScore.text = "${Math.round(city.nomadScore * 10.0) / 10.0}"
            Picasso.get().load("https://nomadlist.com${city.image}").into(cityView.cityImage);
            cityView.setOnClickListener {
                startActivity(Intent(context, CityActivity::class.java).apply { putExtra("detail", city) })
            }
            llCityList.addView(cityView)
        }
    }
}