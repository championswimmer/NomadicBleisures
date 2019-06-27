package com.booking.nomadicbleisures

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.booking.nomadicbleisures.models.Coworking
import com.booking.nomadicbleisures.models.Hotel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_combo.view.*
import java.util.*

class ComboView(val ctx: Context, val attrs: AttributeSet? = null, val layoutId: Int = R.layout.item_combo): FrameLayout(ctx, attrs) {

    private lateinit var rootview: View
    private val r = Random()

    init {
        LayoutInflater.from(ctx).inflate(layoutId, this, true)
    }

    val discountTexts = arrayOf(
        "More than ${(r.nextInt(2) * 10) + 10}% below usual prices",
        "Last ${r.nextInt(7)} rooms left this month",
        "Valid only for today"
    )

    fun setup(coworking: Coworking, hotel: Hotel) {
        Picasso.get().load(coworking.image).into(this.cwImage)
        this.cwTitle?.text = coworking.name
        this.cwRating?.text = coworking.rating.toString()
        this.cwPrice?.text = "${coworking.currency} ${coworking.monthlyPrice.toFloat().toInt()} /mo"
        this.hotelImage?.let {
            Picasso.get().load(hotel.image).into(it)
        }
        this.hotelTitle?.text = hotel.name
        this.hotelPrice?.text = "${hotel.currency} ${hotel.price.toFloat().toInt()}"
        this.hotelRating?.text = hotel.rating.toString()
        this.discountText?.text = discountTexts[r.nextInt(3)]
    }
}