package com.joe.flightinfo.helper

import android.content.Context
import android.util.Log
import com.joe.flightinfo.R
import java.io.BufferedReader
import java.io.InputStreamReader

class AirportHelper private constructor(private val context: Context) {
    companion object {
        @Volatile
        private var instance: AirportHelper? = null

        fun getInstance(context: Context): AirportHelper {
            return instance ?: synchronized(this) {
                instance ?: AirportHelper(context).also { instance = it }
            }
        }
    }

    fun queryZhTwCityById(queryId: String): String? {
        val csvResourceId = R.raw.airports
        val inputStream = context.resources.openRawResource(csvResourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))

        try {
            var line: String?
            while (reader.readLine().also { line = it } != null) {

                val columns = line?.split(",") ?: emptyList()

                if (columns.size == 3 && columns[0] == queryId) {
                    Log.i("FlightInfo", "queryZhTwCityById $queryId ${columns[1]}")
                    return columns[1] // return 'zh-tw-city'
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

        return "Unknown"
    }
}