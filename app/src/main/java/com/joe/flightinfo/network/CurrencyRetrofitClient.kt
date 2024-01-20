package com.joe.flightinfo.network

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyRetrofitClient {
    companion object {

        val baseUrl = "https://api.freecurrencyapi.com"

        fun getCurrencyRetrofitInstance(): Retrofit {

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