package com.booking.nomadicbleisures

import android.app.DatePickerDialog
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
            activity?.supportFragmentManager?.beginTransaction()?.add(
                R.id.container, ListingsFragment.newInstance(
                    ListingsFragment.Companion.SearchType.COWORKING
                )
            )?.addToBackStack(null)?.commit()
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
            cityView.cityNomadScore.text = "${Math.round(city.nomadScore * 10.0) / 10.0}/5"
            Picasso.get().load("https://nomadlist.com${city.image}").into(cityView.cityImage);
            llCityList.addView(cityView)
        }
    }
}