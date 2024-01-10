package com.joe.flightinfo.data

import com.joe.flightinfo.network.ApiEndPoint
import com.joe.flightinfo.network.RetrofitClient

class FlightInfoRepository {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(ApiEndPoint::class.java)

    suspend fun getAllDepartureFlightRepository(airPortID: String) =
        retrofit.getDepartureFlightInfo(airPortID)

    suspend fun getAllArrivalFlightRepository(airPortID: String) =
        retrofit.getArrivalFlightInfo(airPortID)
}