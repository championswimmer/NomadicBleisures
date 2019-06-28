package com.booking.nomadicbleisures.map

import android.graphics.Point
import java.util.*

interface MapDrawComplete {
    fun onDrawComplete(coordinates: ArrayList<Point>, closedPolygon: Boolean, targetPoint: Point)
}
