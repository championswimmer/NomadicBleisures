package com.booking.nomadicbleisures

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.booking.nomadicbleisures.models.Coworking
import com.booking.nomadicbleisures.models.Hotel
import com.booking.nomadicbleisures.network.ApiClient
import com.booking.nomadicbleisures.network.ApiClient2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_listings.*
import kotlinx.android.synthetic.main.fragment_listings.view.*
import kotlinx.android.synthetic.main.item_listing.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingsFragment : Fragment() {

    private lateinit var rootView: View
    var cityId: String = ApiClient.CITY_ID_MUMBAI

    companion object {

        val SEARCH_TYPE = "search_type"

        enum class SearchType {
            HOTELS, COWORKING
        }

        fun newInstance(searchType: SearchType, cityId: String): ListingsFragment {
            return ListingsFragment().apply {
                arguments = Bundle().apply { putString(SEARCH_TYPE, searchType.name)
                putString("cityId", cityId)}
            }
        }
    }

    lateinit var searchType: SearchType

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_listings, container, false)
        searchType = SearchType.valueOf(arguments!!.getString(ListingsFragment.SEARCH_TYPE)!!)
        fetchListings()
        return rootView
    }

    private fun fetchListings() {
        cityId = arguments!!.getString("cityId")!!
        when (searchType) {
            Companion.SearchType.COWORKING -> {
                ApiClient2.coworkingApi.getCoworkingSpaces(cityId).enqueue(object: Callback<List<Coworking>> {
                    override fun onResponse(call: Call<List<Coworking>>, response: Response<List<Coworking>>) {
                        rootView.progressBar.visibility = View.GONE
                        rootView.llListings.visibility = View.VISIBLE
                        response.body()?.let {
                            showCoworkingSpaces(it)
                        }
                    }

                    override fun onFailure(call: Call<List<Coworking>>, t: Throwable) {

                    }
                })
            }
            Companion.SearchType.HOTELS -> {
                ApiClient.hotelsApi.getHotels(cityId).enqueue(object: Callback<List<Hotel>> {
                    override fun onResponse(call: Call<List<Hotel>>, response: Response<List<Hotel>>) {
                        rootView.progressBar.visibility = View.GONE
                        rootView.llListings.visibility = View.VISIBLE
                        response.body()?.let {
                            showHotels(it)
                        }
                    }
                    override fun onFailure(call: Call<List<Hotel>>, t: Throwable) {}
                })
            }
        }
    }

    private fun showHotels(hotels: List<Hotel>) {
        for (hotel in hotels) {
            val listingView = LayoutInflater.from(activity!!).inflate(R.layout.item_listing, llListings, false)
            listingView.listingTitle.text = hotel.name
            hotel.price?.let {
                listingView.listingPrice.text = "${hotel.currency} ${hotel.price.toInt()}"
            }
            listingView.listingRating.text = "${Math.round(hotel.rating * 10.0) / 10.0}"
            listingView.listingSubtitle.text = "${hotel.numCoworking} coworking spaces nearby"
            listingView.listingSubtitle.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    add(
                        R.id.container, ListingsFragment.newInstance(
                            ListingsFragment.Companion.SearchType.COWORKING,
                            cityId
                        )
                    )
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    addToBackStack(null)
                    commit()
                    CheckoutActivity.hotel = hotel
                }
            }
            listingView.setOnClickListener {
                CheckoutActivity.hotel = hotel
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(hotel.deeplink)
                    )
                )
            }
            Picasso.get().load(hotel.image).into(listingView.listingImage);
            llListings.addView(listingView)
        }
    }

    private fun showCoworkingSpaces(coworkingSpaces: List<Coworking>) {
        for (coworking in coworkingSpaces) {
            val listingView = LayoutInflater.from(activity!!).inflate(R.layout.item_listing, llListings, false)
            listingView.listingTitle.text = coworking.name
            coworking.monthlyPrice?.let {
                listingView.listingPrice.text = "${coworking.currency} ${coworking.monthlyPrice.toFloat().toInt()} /mo"
            }
            listingView.listingRating.text = "${Math.round(coworking.rating * 10.0) / 10.0}"
            listingView.listingSubtitle.text = "See hotels nearby"
            Picasso.get().load(coworking.image).into(listingView.listingImage);
            listingView.setOnClickListener {
                CheckoutActivity.coworking = coworking
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    add(R.id.container, CoworkingDetailFragment.newInstance(coworking))
                    addToBackStack(null)
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    commit()
                }
            }
            llListings.addView(listingView)
        }
    }
}