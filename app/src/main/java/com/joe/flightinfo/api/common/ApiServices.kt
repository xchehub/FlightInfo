package com.joe.flightinfo.api.common

import com.joe.flightinfo.api.model.*
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiServices {
    companion object {
        // depart
        const val depart = "D/{airPortID}"
        // arrival
        const val arrival = "A/{airPortID}"
    }

    @GET(depart)
    fun geDepartureFlightInfo(@Path("airPortID") id: String): Deferred<BaseResponse<AirPortFlyInfoModel>>

    @GET(arrival)
    fun getArrivalFlightInfo(@Path("airPortID") id: String): Deferred<BaseResponse<AirPortFlyInfoModel>>
}