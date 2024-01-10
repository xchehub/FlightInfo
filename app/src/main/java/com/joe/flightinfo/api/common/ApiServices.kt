package com.joe.flightinfo.api.common

import com.joe.flightinfo.api.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    companion object {
        // depart
        const val depart = "D/{airPortID}"
        // arrival
        const val arrival = "A/{airPortID}"
    }

    @GET(depart)
//    fun geDepartureFlightInfo(@Path("airPortID") id: String): Deferred<BaseResponse<AirPortFlyInfoModel>>
    fun getDepartureFlightInfo(@Path("airPortID") id: String): Response<FlightResponseModel>

    @GET(arrival)
//    fun getArrivalFlightInfo(@Path("airPortID") id: String): Deferred<BaseResponse<AirPortFlyInfoModel>>
    fun getArrivalFlightInfo(@Path("airPortID") id: String): Response<FlightResponseModel>
}