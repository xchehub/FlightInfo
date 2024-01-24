package com.joe.flightinfo.network

import com.joe.flightinfo.data.model.TdxAccessTokenInfo
import com.joe.flightinfo.data.model.TdxFlightArrivalInfoItem
import com.joe.flightinfo.data.model.TdxFlightDepartureInfoItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TdxApiEndPoint {
    companion object {
        // depart
        const val depart = "Departure/{airPortID}"

        // arrival
        const val arrival = "Arrival/{airPortID}"
    }

    @POST("token/")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Deferred<Response<TdxAccessTokenInfo>>

    @POST("token/")
    @FormUrlEncoded
    suspend fun getAccessTokenA(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Response<TdxAccessTokenInfo>

    @Headers("Accept: application/json")
    @GET(depart)
    suspend fun getDepartureFlightInfo(
        @Path("airPortID") id: String,
        @Header("authorization") token: String,
        @Query("top") top: String,
        @Query("format") format: String
    ): Response<ArrayList<TdxFlightDepartureInfoItem>>

    @Headers("Accept: application/json")
    @GET(arrival)
    suspend fun getArrivalFlightInfo(
        @Path("airPortID") id: String,
        @Header("authorization") token: String,
        @Query("top") top: String,
        @Query("format") format: String
    ): Response<ArrayList<TdxFlightArrivalInfoItem>>
}