package com.joe.flightinfo.network

import com.joe.flightinfo.data.model.FlightInfoModel
import com.joe.flightinfo.data.model.FlightInfoModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface ApiEndPoint {
    companion object {
        // depart
        const val depart = "D/{airPortID}"

        // arrival
        const val arrival = "A/{airPortID}"
    }

//    @Headers("Accept: application/json")
    @GET(depart)
//    suspend fun getDepartureFlightInfo(@Path("airPortID") id: String): Response<FlightInfoModel>
    suspend fun getDepartureFlightInfo(@Path("airPortID") id: String): Response<ArrayList<FlightInfoModelItem>>

//    @Headers("Accept: application/json")
    @GET(arrival)
//    suspend fun getArrivalFlightInfo(@Path("airPortID") id: String): Response<<FlightInfoModel>
    suspend fun getArrivalFlightInfo(@Path("airPortID") id: String): Response<ArrayList<FlightInfoModelItem>>
}