package com.joe.flightinfo.data

import com.joe.flightinfo.api.common.ApiServices
import com.joe.flightinfo.api.common.RetrofitClient

class FlightInfoRepository {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(ApiServices::class.java)

    suspend fun getAllDepartureFlightRepository(airPortID : String) = retrofit.getDepartureFlightInfo(airPortID)

    suspend fun getAllArrivalFlightRepository(airPortID : String) = retrofit.getArrivalFlightInfo(airPortID)
}