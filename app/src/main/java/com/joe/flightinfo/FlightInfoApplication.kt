package com.joe.flightinfo

import android.app.Application
import com.joe.flightinfo.helper.AirlineHelper
import com.joe.flightinfo.helper.AirportHelper
import com.joe.flightinfo.helper.TokenHelper

class FlightInfoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        airportHelper = AirportHelper.getInstance(baseContext)
        airlineHelper = AirlineHelper.getInstance(baseContext)
    }

    companion object {
        lateinit var airportHelper: AirportHelper

        lateinit var airlineHelper: AirlineHelper

        lateinit var accessTokenHelper: TokenHelper
    }
}