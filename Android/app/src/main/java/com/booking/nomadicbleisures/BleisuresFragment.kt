package com.booking.nomadicbleisures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.booking.nomadicbleisures.models.NomadCity
import com.booking.nomadicbleisures.network.ApiClient
import com.booking.nomadicbleisures.network.ApiClient2
import com.booking.nomadicbleisures.utils.IOUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_bleisures.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BleisuresFragment: Fragment() {

    lateinit var adapter: BleisuresAdapter

    companion object {

        fun newInstance(queryString: String): BleisuresFragment {
            return BleisuresFragment().apply {
                arguments = Bundle().apply { putString("query", queryString) }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_bleisures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = BleisuresAdapter()
        rvBleisures.layoutManager = LinearLayoutManager(activity)
        rvBleisures.adapter = adapter

        val query = arguments!!.getString("query")
        val url = "http://178.128.249.124:4242/bleisures?" + query
        ApiClient2.bleisuresApi.getBlesiures(url).enqueue(object : Callback<List<NomadCity>> {
            override fun onResponse(call: Call<List<NomadCity>>, response: Response<List<NomadCity>>) {
                response.body()?.let {
                    progressBar.visibility = View.GONE
                    adapter.updateData(it)
                }
            }

            override fun onFailure(call: Call<List<NomadCity>>, t: Throwable) {}
        })
    }
}