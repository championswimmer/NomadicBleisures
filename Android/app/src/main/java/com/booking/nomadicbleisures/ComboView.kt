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

class ComboView(val ctx: Context, val attrs: AttributeSet?): FrameLayout(ctx, attrs) {

    private lateinit var rootview: View
    private val r = Random()

    val discountTexts = arrayOf(
        "More than ${(r.nextInt(2) * 10) + 10}% below usual prices",
        "Last ${r.nextInt(7)} rooms left this month",
        "Valid only for today"
    )

    fun setup(coworking: Coworking, hotel: Hotel, layoutId: Int = R.layout.item_combo) {
        LayoutInflater.from(ctx).inflate(layoutId, this, true)
        Picasso.get().load(coworking.image).into(this.cwImage)
        this.cwTitle?.text = coworking.name
        this.cwRating?.text = coworking.rating.toString()
        coworking.monthlyPrice?.let {
            this.cwPrice?.text = "${coworking.currency} ${coworking.monthlyPrice} /mo"
        }
        this.hotelImage?.let {
            Picasso.get().load(hotel.image).into(it)
        }
        this.hotelTitle?.text = hotel.name
        hotel.price?.let {
            this.hotelPrice?.text = "${hotel.currency} ${hotel.price.toInt()}"
        }
        this.hotelRating?.text = hotel.rating.toString()
        this.discountText?.text = discountTexts[r.nextInt(3)]
    }
}