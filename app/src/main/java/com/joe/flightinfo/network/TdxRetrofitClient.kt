package com.joe.flightinfo.network

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TdxRetrofitClient {
    companion object {

        val baseUrl = "https://tdx.transportdata.tw/auth/realms/TDXConnect/protocol/openid-connect/"
        val flightBaseURL = "https://tdx.transportdata.tw/api/basic/v2/Air/FIDS/Airport/"

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

        fun getFlightRetrofitInstance(): Retrofit {

            val builder = OkHttpClient.Builder()
            builder.addInterceptor(OkHttpProfilerInterceptor())
            val client = builder.build()

            return Retrofit.Builder()
                .baseUrl(flightBaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}