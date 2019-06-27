package com.booking.nomadicbleisures

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booking.nomadicbleisures.models.NomadCity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_city.view.*
import kotlinx.android.synthetic.main.item_city_combo.view.*
import kotlinx.android.synthetic.main.item_title.view.*

class BleisuresAdapter : RecyclerView.Adapter<BleisuresAdapter.ViewHolder>() {

    var items: List<NomadCity> = arrayListOf()

    val VIEW_TYPE_RECOMMENDED = 0
    val VIEW_TYPE_NORMAL = 1
    val VIEW_TYPE_TITLE_RECOMMENDED = 2
    val VIEW_TYPE_TITLE_OTHER = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            if (viewType == VIEW_TYPE_RECOMMENDED) LayoutInflater.from(parent.context).inflate(
                R.layout.item_city_combo,
                parent,
                false
            ).apply {
                cityRootView?.apply {
                    layoutParams.height = layoutParams.height - 170
                }
            }
            else if (viewType == VIEW_TYPE_NORMAL) LayoutInflater.from(parent.context).inflate(
                R.layout.item_city,
                parent,
                false
            )
            else LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_RECOMMENDED -> {
                val city = items[position - 1]
                holder.rootView.apply {
                    cityTitle.text = city.name
                    cityPrice.text = "$${city.price}"
                    cityWeather.text = "${city.temperature}C"
                    cityInternet.text = "${city.internetSpeed}mbps"
                    cityNomadScore.text = "${Math.round(city.nomadScore * 10.0) / 10.0}"
                    Picasso.get().load("https://nomadlist.com${city.image}").into(cityImage);
                    cityRootView.setOnClickListener {
                        context.startActivity(Intent(context, CityActivity::class.java).apply { putExtra("detail", city) })
                    }

                    city.combos?.let {
                        for (combo in it) {
                            cityCombos.addView(
                                ComboView(
                                    context,
                                    null,
                                    R.layout.item_combo_compact
                                ).apply { setup(combo.coworking, combo.hotel) })
                        }
                    }

                }
            }
            VIEW_TYPE_NORMAL -> {
                val city = items[position - 2]
                holder.rootView.apply {
                    cityTitle.text = city.name
                    cityPrice.text = "$${city.price}"
                    cityWeather.text = "${city.temperature}C"
                    cityInternet.text = "${city.internetSpeed}mbps"
                    cityNomadScore.text = "${Math.round(city.nomadScore * 10.0) / 10.0}"
                    Picasso.get().load("https://nomadlist.com${city.image}").into(cityImage);
                    cityRootView.setOnClickListener {
                        context.startActivity(Intent(context, CityActivity::class.java).apply { putExtra("detail", city) })
                    }
                }
            }
            VIEW_TYPE_TITLE_RECOMMENDED -> {
                holder.rootView.apply {
                    title.text = "Recommended nomadic bleisures"
                }
            }
            VIEW_TYPE_TITLE_OTHER -> {
                holder.rootView.apply {
                    title.text = "Other cities you might want to consider"
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + 2
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val rootView = v
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_TITLE_RECOMMENDED
        else if (position == 1) VIEW_TYPE_RECOMMENDED
        else if (position == 2) VIEW_TYPE_TITLE_OTHER else VIEW_TYPE_NORMAL
    }

    fun updateData(items: List<NomadCity>) {
        this.items = items
        notifyDataSetChanged()
    }
}