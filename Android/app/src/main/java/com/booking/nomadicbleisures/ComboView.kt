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

class ComboView(val ctx: Context, attrs: AttributeSet?): FrameLayout(ctx, attrs) {

    private lateinit var rootview: View
    private val r = Random()

    init {
        LayoutInflater.from(ctx).inflate(R.layout.item_combo, this, true)
    }

    val discountTexts = arrayOf(
        "More than ${(r.nextInt(2) * 10) + 10}% below usual prices",
        "Last ${r.nextInt(7)} rooms left this month",
        "Valid only for today"
    )

    fun setup(coworking: Coworking, hotel: Hotel) {
        Picasso.get().load(coworking.image).into(this.cwImage)
        this.cwTitle.text = coworking.name
        this.cwRating.text = coworking.rating.toString()
        this.cwPrice.text = "${coworking.currency} ${coworking.monthlyPrice.toFloat().toInt()} /mo"
        Picasso.get().load(hotel.image).into(this.hotelImage)
        this.hotelTitle.text = hotel.name
        this.hotelPrice.text = "${hotel.currency} ${hotel.price.toFloat().toInt()}"
        this.hotelRating.text = hotel.rating.toString()
        this.discountText.text = discountTexts[r.nextInt(3)]
    }
}