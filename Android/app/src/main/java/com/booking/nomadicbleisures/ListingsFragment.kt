package com.booking.nomadicbleisures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ListingsFragment: Fragment() {

    companion object {

        val SEARCH_TYPE = "search_type"

        enum class SearchType {
            HOTELS, COWORKING
        }

        fun newInstance(searchType: SearchType) {
            ListingsFragment().apply {
                arguments = Bundle().apply { putString(SEARCH_TYPE, searchType.name) }
            }
        }
    }

    lateinit var searchType: SearchType

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_listings, container, false)
        searchType = SearchType.valueOf(arguments!!.getString(ListingsFragment.SEARCH_TYPE)!!)
        return view
    }
}