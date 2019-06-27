package com.booking.nomadicbleisures.models

data class Filters(val selections: List<Selection>,
                   val filters: List<Filter>) {

    data class Selection(val title: String,
                         val type: String,
                         val filters: List<String>)

    data class Filter(val type: String,
                      val title: String)
}