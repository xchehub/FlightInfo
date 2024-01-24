package com.joe.flightinfo

import android.app.Application
import com.joe.flightinfo.helper.AirlineHelper
import com.joe.flightinfo.helper.AirportHelper

class FlightInfoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        airportHelper = AirportHelper.getInstance(baseContext)
        airlineHelper = AirlineHelper.getInstance(baseContext)
    }

    companion object {
        lateinit var airportHelper: AirportHelper
            private set

        lateinit var airlineHelper: AirlineHelper
            private set
    }
}