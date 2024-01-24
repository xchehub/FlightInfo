package com.joe.flightinfo.helper

import android.content.Context
import android.util.Log
import com.joe.flightinfo.R
import java.io.BufferedReader
import java.io.InputStreamReader

class AirlineHelper private constructor(private val context: Context)  {
    companion object {
        @Volatile
        private var instance: AirlineHelper? = null

        fun getInstance(context: Context): AirlineHelper {
            return instance ?: synchronized(this) {
                instance ?: AirlineHelper(context).also { instance = it }
            }
        }
    }

    fun queryAirlineById(queryId: String): String {
        val csvResourceId = R.raw.airlines
        val inputStream = context.resources.openRawResource(csvResourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))

        try {
            var line: String?
            while (reader.readLine().also { line = it } != null) {

                val columns = line?.split(",") ?: emptyList()

                if (columns.size == 2 && columns[0] == queryId) {
                    Log.i("FlightInfo", "queryZhTwCityById $queryId ${columns[1]}")
                    return columns[1] // return 'zh-tw-airline'
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                reader.close()
                inputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return "不知名航空"
    }
}