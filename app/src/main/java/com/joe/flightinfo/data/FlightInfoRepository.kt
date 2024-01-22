package com.joe.flightinfo.data

import com.joe.flightinfo.network.ApiEndPoint
import com.joe.flightinfo.network.RetrofitClient
import com.joe.flightinfo.network.TdxApiEndPoint
import com.joe.flightinfo.network.TdxRetrofitClient

class FlightInfoRepository {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(ApiEndPoint::class.java)
    private val tdxRetrofit = TdxRetrofitClient.getRetrofitInstance().create(TdxApiEndPoint::class.java)

    suspend fun getAllDepartureFlightRepository(airPortID: String) =
        retrofit.getDepartureFlightInfo(airPortID)

    suspend fun getAllArrivalFlightRepository(airPortID: String) =
        retrofit.getArrivalFlightInfo(airPortID)

    suspend fun getTdxAccessToken(grantType: String, clientId: String, clientSecret: String) =
        tdxRetrofit.getAccessToken(grantType, clientId, clientSecret)
}