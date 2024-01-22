package com.joe.flightinfo.data

import com.joe.flightinfo.network.CurrencyApiEndPoint
import com.joe.flightinfo.network.CurrencyRetrofitClient

class CurrencyInfoRepository {
    private val retrofit = CurrencyRetrofitClient.getCurrencyRetrofitInstance().create(
        CurrencyApiEndPoint::class.java)

    suspend fun getAllCurrencyRepository() =
        retrofit.getCurrencies()
    suspend fun getExchangedCurrency(base: String, currency: String) =
        retrofit.exchangeRateConvert(base, currency)
}