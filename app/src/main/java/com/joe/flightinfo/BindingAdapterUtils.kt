package com.joe.flightinfo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//val airlineLogoMap = mapOf<String, Int>(
//    "JX" to R.drawable.jx,
//    "" to ""
//)

//val LOG_BASE_URL = "https://github.com/xchehub/flightinfo/tree/main/airlogos/"
val LOGO_BASE_URL = "https://cdn.statically.io/gh/xchehub/flightinfo/main/airlogos/"

@BindingAdapter("loadImage")
fun loadImage(thumbimg : ImageView, airlineID : String){
    val logoURL = "$LOGO_BASE_URL$airlineID.png"

    Glide.with(thumbimg)
        .load(logoURL)
        .circleCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .fallback(R.drawable.ic_launcher_foreground)
        .into(thumbimg)

}