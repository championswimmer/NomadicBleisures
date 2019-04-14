package com.booking.nomadicbleisures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.booking.nomadicbleisures.models.Coworking
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_coworking_detail.*
import kotlinx.android.synthetic.main.item_listing.view.*

class CoworkingDetailFragment: Fragment() {

    private lateinit var rootView: View
    private lateinit var coworking: Coworking

    companion object {
        fun newInstance(coworking: Coworking): CoworkingDetailFragment {
            return CoworkingDetailFragment().apply {
                arguments = Bundle().apply { putParcelable("detail", coworking) }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_coworking_detail, container, false)
        coworking = arguments!!.getParcelable("detail")!!
        Picasso.get().load(coworking.image).into(rootView.listingImage)
        rootView.listingTitle.text = coworking.name
        rootView.listingPrice.text = "${coworking.currency}${coworking.monthlyPrice.toFloat().toInt()} /mo"
        rootView.listingRating.text = "${Math.round(coworking.rating * 10.0) / 10.0}"
        return rootView
    }
}