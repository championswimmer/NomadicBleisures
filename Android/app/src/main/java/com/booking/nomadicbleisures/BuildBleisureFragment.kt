package com.booking.nomadicbleisures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.booking.nomadicbleisures.models.Filters
import com.booking.nomadicbleisures.network.ApiClient2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_build_bleisures.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BuildBleisureFragment : BottomSheetDialogFragment() {

    var currentSelectionIndex = 0
    var filtersAdded = false
    lateinit var selections: List<Filters.Selection>
    lateinit var filters: List<Filters.Filter>

    private var rootView: View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_build_bleisures, container, false)
        return rootView
    }

    override fun onStart() {
        super.onStart()
        val peekHeight = 2000
        val mBehavior = BottomSheetBehavior.from(rootView!!.parent as View)
        mBehavior.peekHeight = peekHeight
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiClient2.metaApi.getFilters().enqueue(object : Callback<Filters> {
            override fun onResponse(call: Call<Filters>, response: Response<Filters>) {
                response.body()?.let {bleisureFilter ->
                    selections = bleisureFilter.selections
                    filters = bleisureFilter.filters
                    addSelection()
                }
            }

            override fun onFailure(call: Call<Filters>, t: Throwable) {}
        })

        btn_search.setOnClickListener {
           searchBleisure()
        }
    }

    private fun addSelection() {
        val selection = selections[currentSelectionIndex]
        val selectionLayout =
            (LayoutInflater.from(activity).inflate(R.layout.selection, filter_container, false) as LinearLayout).apply {
                findViewById<TextView>(R.id.selection_title).text = selection.title
                for (filter in selection.filters)
                    findViewById<ChipGroup>(R.id.filter_container).addView(
                        LayoutInflater.from(activity).inflate(
                            R.layout.chip_selection,
                            this,
                            false
                        ).apply {
                            findViewById<Chip>(R.id.chip).apply {
                                text = filter.title
                                setOnClickListener {
                                    if (currentSelectionIndex < selections.size - 1) {
                                        currentSelectionIndex++
                                        filter.selected = true
                                        addSelection()
                                    } else {
                                        btn_search.visibility = View.VISIBLE
                                        if (!filtersAdded)
                                            showFilters()
                                    }
                                }
                            }
                        })

            }
        filter_container.addView(selectionLayout)
        space.layoutParams.height = space.height + 50
    }

    private fun showFilters() {
        filtersAdded = true
        val filtersLayout = (LayoutInflater.from(activity).inflate(R.layout.filters, filter_container, false) as LinearLayout).apply {
            for (filter in filters) {
                findViewById<ChipGroup>(R.id.filter_container).addView(
                    LayoutInflater.from(activity).inflate(
                        R.layout.chip_filter,
                        this,
                        false
                    ).apply {
                        findViewById<Chip>(R.id.chip).apply {
                            text = filter.title
                            setOnClickListener {
                                filter.selected = true
                            }
                        }
                    })
            }
        }
        filter_container.addView(filtersLayout)
        space.visibility = View.GONE
    }

    private fun searchBleisure() {
        var queryString = ""
        var count = 0
        for (selection in selections) {
            for (filter in selection.filters) {
                if (filter.selected) {
                    queryString += "${if (count == 0) "" else "&" }${selection.type}=${filter.value}"
                    count++
                }
            }
        }
        for (filter in filters) {
            if (filter.selected) {
                count++
                queryString += "&${filter.type}=true"
            }
        }
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            add(
                R.id.container, BleisuresFragment.newInstance(queryString)
            )
            addToBackStack(null)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
            dismiss()
        }
    }
}