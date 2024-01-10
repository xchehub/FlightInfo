package com.joe.flightinfo.api.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{

        val baseUrl = "https://e-traffic.taichung.gov.tw/DataAPI/api/AirPortFlyAPI/"

        fun getRetrofitInstance(): Retrofit {

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}