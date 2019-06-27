package com.booking.nomadicbleisures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentTransaction
import com.booking.nomadicbleisures.models.Filters
import com.booking.nomadicbleisures.utils.IOUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_build_bleisures.*


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

        val filtersType = object : TypeToken<Filters>() {}.type
        val bleisureFilter = Gson().fromJson(IOUtils.loadJSONFromAsset(activity!!, "filters.json"), filtersType)
                as Filters

        selections = bleisureFilter.selections
        filters = bleisureFilter.filters

        addSelection()

        btn_search.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                add(
                    R.id.container, BleisuresFragment()
                )
                addToBackStack(null)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                commit()
                dismiss()
            }
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
                                text = filter
                                setOnClickListener {
                                    if (currentSelectionIndex < selections.size - 1) {
                                        currentSelectionIndex++
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
                        }
                    })
            }
        }
        filter_container.addView(filtersLayout)
        space.visibility = View.GONE

    }
}