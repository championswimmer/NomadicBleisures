package com.booking.nomadicbleisures.models

import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("selections") val selections: List<Selection>,
    @SerializedName("options") val filters: List<Filter>
) {

    data class Selection(
        val title: String,
        val type: String,
        @SerializedName("options") val filters: List<Option>
    )

    data class Filter(
        val type: String,
        val title: String,
        var selected: Boolean = false
    )

    data class Option(
        @SerializedName("value") val value: String,
        @SerializedName("title") val title: String,
        var selected: Boolean = false
    )
}