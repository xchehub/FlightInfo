package com.joe.flightinfo.helper

import android.content.Context

const val PREFERENCE_NAME = "flight_info"
const val BASE_AIRPORT = "base_airport"
const val BASE_CURRENCY = "base_currency"

object SharePreferenceHelper {
    fun setBaseAirport(context: Context, baseAirPort: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(BASE_AIRPORT, baseAirPort).apply()
    }

    fun getBaseAirport(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(BASE_AIRPORT, "")
    }

    fun setBaseCurrency(context: Context, baseAirPort: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(BASE_CURRENCY, baseAirPort).apply()
    }

    fun getBaseCurrency(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(BASE_CURRENCY, "")
    }
}