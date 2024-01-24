package com.joe.flightinfo.data

import com.joe.flightinfo.BuildConfig
import com.joe.flightinfo.GlobalAccessToken
import com.joe.flightinfo.data.model.TdxFlightArrivalInfoItem
import com.joe.flightinfo.network.ApiEndPoint
import com.joe.flightinfo.network.RetrofitClient
import com.joe.flightinfo.network.TdxApiEndPoint
import com.joe.flightinfo.network.TdxRetrofitClient
import com.joe.flightinfo.ui.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FlightInfoRepository() {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(ApiEndPoint::class.java)
    private val tdxRetrofit = TdxRetrofitClient.getRetrofitInstance().create(TdxApiEndPoint::class.java)
    private val tdxFlightRetrofit = TdxRetrofitClient.getFlightRetrofitInstance().create(TdxApiEndPoint::class.java)

    suspend fun getAllDepartureFlightRepository(airPortID: String) =
        tdxFlightRetrofit.getDepartureFlightInfo(airPortID, "Bearer ${GlobalAccessToken.token}", "30", "JSON")

    suspend fun getAllArrivalFlightRepository(airPortID: String): Response<ArrayList<TdxFlightArrivalInfoItem>> {
        getTdxAccessToken()

        return tdxFlightRetrofit.getArrivalFlightInfo(airPortID, "Bearer ${GlobalAccessToken.token}", "30", "JSON")
    }

    private fun getTdxAccessToken() {
        if (GlobalAccessToken.token.isNotEmpty()
            && System.currentTimeMillis() - GlobalAccessToken.expireTime > 86400 * 1000 ) {
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
                try {

                    val response = tdxRetrofit.getAccessTokenA(
                        "client_credentials",
                        BuildConfig.TDX_CLIENT_ID,
                        BuildConfig.TDX_CLIENT_SECRET)

                    if (response.isSuccessful) {
                        GlobalAccessToken.token = Result.Success(response.body()!!).data.access_token
                        GlobalAccessToken.expireTime = System.currentTimeMillis()
                    } else {
//                        accessTokenResponseData.value = Result.Error(response.message())
                    }
                } catch (e: Exception) {
//                    accessTokenResponseData.value = Result.ErrorException(e)
                }
            }
//        }
    }
}