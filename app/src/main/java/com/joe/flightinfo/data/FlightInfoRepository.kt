package com.joe.flightinfo.data

import android.icu.text.SimpleDateFormat
import com.joe.flightinfo.GlobalAccessToken
import com.joe.flightinfo.network.TdxApiEndPoint
import com.joe.flightinfo.network.TdxRetrofitClient
import java.util.Date

class FlightInfoRepository() {
    private val tdxFlightRetrofit = TdxRetrofitClient.getFlightRetrofitInstance().create(TdxApiEndPoint::class.java)

    suspend fun getAllDepartureFlightRepository(airPortID: String) =
        tdxFlightRetrofit.getDepartureFlightInfo(airPortID, "Bearer ${GlobalAccessToken.token}", "50", "JSON", "date(FlightDate) eq ${getTodayDate()}", "ActualDepartureTime desc")

    suspend fun getAllArrivalFlightRepository(airPortID: String) =
        tdxFlightRetrofit.getArrivalFlightInfo(airPortID, "Bearer ${GlobalAccessToken.token}", "50", "JSON", "date(FlightDate) eq ${getTodayDate()}", "ActualArrivalTime desc")

    private fun getTodayDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        return formatter.format(date)
    }

}