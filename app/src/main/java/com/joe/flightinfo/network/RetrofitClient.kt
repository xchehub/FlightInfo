package com.joe.flightinfo.network

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {

        val baseUrl = "https://e-traffic.taichung.gov.tw/DataAPI/api/AirPortFlyAPI/"

        fun getRetrofitInstance(): Retrofit {

            val builder = OkHttpClient.Builder()
            builder.addInterceptor(OkHttpProfilerInterceptor())
            val client = builder.build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}