package com.joe.flightinfo.network

import com.joe.flightinfo.BuildConfig
import com.joe.flightinfo.data.model.CurrenciesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyApiEndPoint {

    companion object {
        // latest api
        const val latestapi = "v1/latest"
    }

    @Headers("apikey: " + BuildConfig.CURRENCIES_APIKEY)
    @GET(latestapi)
    suspend fun getCurrencies(): Response<CurrenciesModel>

    @Headers("apikey: " + BuildConfig.CURRENCIES_APIKEY)
    @GET(latestapi)
    suspend fun exchangeRateConvert(
        @Query("base_currency") base: String,
        @Query("currencies") currency: String
    ): Response<CurrenciesModel>

}