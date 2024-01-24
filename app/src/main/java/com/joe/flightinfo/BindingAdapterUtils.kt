package com.joe.flightinfo

import android.app.Application
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.joe.flightinfo.helper.AirportHelper

val LOGO_BASE_URL = "https://cdn.statically.io/gh/xchehub/flightinfo/main/airlogos/"

@BindingAdapter("loadImage")
fun loadImage(thumbimg: ImageView, airlineID: String) {
    val logoURL = "$LOGO_BASE_URL$airlineID.png"

    Glide.with(thumbimg)
        .load(logoURL)
        .circleCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .fallback(R.drawable.ic_launcher_foreground)
        .into(thumbimg)
}

@BindingAdapter("getAirportName")
fun getAirportName(textView: TextView, queryId: String) {
    val airport = FlightInfoApplication.airportHelper.queryZhTwCityById(queryId)
    textView.text = airport
}

@BindingAdapter("getAirlineName")
fun getAirlineName(textView: TextView, queryId: String) {
    val airport = FlightInfoApplication.airlineHelper.queryAirlineById(queryId)
    textView.text = airport
}