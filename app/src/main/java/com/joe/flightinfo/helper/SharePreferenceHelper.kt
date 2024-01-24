package com.joe.flightinfo.helper

import android.content.Context

const val PREFERENCE_NAME = "flight_info"
const val BASE_AIRPORT = "base_airport"
const val BASE_CURRENCY = "base_currency"

const val USD_RATE = "usd_rate"
const val CAD_RATE = "cad_rate"
const val EUR_RATE = "eur_rate"
const val CNY_RATE = "cny_rate"
const val HKD_RATE = "hkd_rate"
const val JPY_RATE = "jpy_rate"

const val ACCESS_TOKEN = "access_token"
const val ACCESS_TOKEN_EXPIRE_TIME = "access_token_expire_time"

object SharePreferenceHelper {
    fun setBaseAirport(context: Context, currency: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(BASE_AIRPORT, currency).apply()
    }

    fun getBaseAirport(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(BASE_AIRPORT, "")
    }

    fun setBaseCurrency(context: Context, currency: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(BASE_CURRENCY, currency).apply()
    }

    fun getBaseCurrency(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(BASE_CURRENCY, "")
    }

    fun setCADRate(context: Context, currency: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(CAD_RATE, currency).apply()
    }

    fun getCADRate(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(CAD_RATE, "")
    }
    fun setEURRate(context: Context, currency: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(EUR_RATE, currency).apply()
    }

    fun getEURRate(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(EUR_RATE, "")
    }
    fun setCNYRate(context: Context, currency: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(CNY_RATE, currency).apply()
    }

    fun getCNYRate(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(CNY_RATE, "")
    }
    fun setHKDRate(context: Context, currency: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(HKD_RATE, currency).apply()
    }

    fun getHKDRate(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(HKD_RATE, "")
    }
    fun setJPYRate(context: Context, currency: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(JPY_RATE, currency).apply()
    }

    fun getJPYRate(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(JPY_RATE, "")
    }

    fun setAccessToken(context: Context, token: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putString(ACCESS_TOKEN, token).apply()
    }

    fun getAccessToken(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(ACCESS_TOKEN, "")
    }

    fun setAccessTokenExpireTime(context: Context, expireTime: Int) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
            .putInt(ACCESS_TOKEN_EXPIRE_TIME, expireTime).apply()
    }

    fun getAccessTokenExpireTime(context: Context): Int {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getInt(ACCESS_TOKEN_EXPIRE_TIME, -1)
    }
}