package com.booking.nomadicbleisures

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.booking.nomadicbleisures.models.Coworking
import com.booking.nomadicbleisures.models.Hotel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.fragment_listings.*
import kotlinx.android.synthetic.main.item_listing.view.*

class CheckoutActivity: AppCompatActivity() {

    companion object {
        var hotel: Hotel? = null
        var coworking: Coworking? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val hotel = hotel!!
        val listingView = LayoutInflater.from(this).inflate(R.layout.item_listing, hotelContainer, false)
        listingView.listingTitle.text = hotel.name
        hotel.price?.let {
            listingView.listingPrice.text = "${hotel.currency} ${hotel.price.toInt()}"
        }
        listingView.listingRating.text = "${Math.round(hotel.rating * 10.0) / 10.0}"
        listingView.listingSubtitle.visibility = View.INVISIBLE
        Picasso.get().load(hotel.image).into(listingView.listingImage);
        hotelContainer.addView(listingView)

        val coworking = coworking!!
        val listingView2 = LayoutInflater.from(this).inflate(R.layout.item_listing, coworkingContainer, false)
        listingView2.listingTitle.text = coworking.name
        coworking.monthlyPrice?.let {
            listingView2.listingPrice.text = "${coworking.currency} ${coworking.monthlyPrice} /mo"
        }
        listingView2.listingRating.text = "${Math.round(coworking.rating * 10.0) / 10.0}"
        listingView2.listingSubtitle.visibility = View.INVISIBLE
        Picasso.get().load(coworking.image).into(listingView2.listingImage);
        coworkingContainer.addView(listingView2)

        price.text = "${hotel.currency} ${hotel.price} + ${coworking.currency} ${coworking.monthlyPrice}"
    }

}