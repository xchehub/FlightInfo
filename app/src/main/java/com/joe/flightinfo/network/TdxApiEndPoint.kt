package com.joe.flightinfo.network

import com.joe.flightinfo.BuildConfig
import com.joe.flightinfo.data.model.FlightInfoModelItem
import com.joe.flightinfo.data.model.TdxAccessTokenInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TdxApiEndPoint {
    companion object {
        // access token
        const val token = "/token"

//        // depart
//        const val depart = "D/{airPortID}"
//
//        // arrival
//        const val arrival = "A/{airPortID}"
    }

    @POST(token)
    @Headers("content-type: application/x-www-form-urlencoded")
    suspend fun getAccessToken(
        @Query("grant_type") grantType: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Response<TdxAccessTokenInfo>

//    //    @Headers("Accept: application/json")
//    @GET(depart)
////    suspend fun getDepartureFlightInfo(@Path("airPortID") id: String): Response<FlightInfoModel>
//    suspend fun getDepartureFlightInfo(@Path("airPortID") id: String): Response<ArrayList<FlightInfoModelItem>>
//
//    //    @Headers("Accept: application/json")
//    @GET(arrival)
////    suspend fun getArrivalFlightInfo(@Path("airPortID") id: String): Response<<FlightInfoModel>
//    suspend fun getArrivalFlightInfo(@Path("airPortID") id: String): Response<ArrayList<FlightInfoModelItem>>
}