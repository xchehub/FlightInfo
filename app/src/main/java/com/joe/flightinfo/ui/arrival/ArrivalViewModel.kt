package com.joe.flightinfo.ui.arrival

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.flightinfo.BuildConfig
import com.joe.flightinfo.GlobalAccessToken
import com.joe.flightinfo.data.FlightInfoRepository
import com.joe.flightinfo.data.adapter.ArrivalDataAdapter
import com.joe.flightinfo.data.model.TdxFlightArrivalInfoItem
import com.joe.flightinfo.helper.SharePreferenceHelper
import com.joe.flightinfo.network.TdxApiEndPoint
import com.joe.flightinfo.network.TdxRetrofitClient
import com.joe.flightinfo.ui.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArrivalViewModel(private val repository: FlightInfoRepository) : ViewModel() {

    private val _flightInfoResponseData = MutableLiveData<Result<ArrayList<TdxFlightArrivalInfoItem>>>()
    val flightInfoResponseData: LiveData<Result<ArrayList<TdxFlightArrivalInfoItem>>> =
        _flightInfoResponseData

    private var arrivalDataAdapter: ArrivalDataAdapter = ArrivalDataAdapter()

    init {
        makeApiCall()
    }

    fun getAdapter(): ArrivalDataAdapter {
        return arrivalDataAdapter
    }

    fun setAdapterData(data: ArrayList<TdxFlightArrivalInfoItem>) {
        arrivalDataAdapter.setData(data)
        arrivalDataAdapter.notifyDataSetChanged()
    }

    private fun makeApiCall(input: String? = null) = viewModelScope.launch {
        // TODO check access token the get flight info

        if (GlobalAccessToken.token.isEmpty()
            && System.currentTimeMillis() - GlobalAccessToken.expireTime > 86400 * 1000 ) {

            val tdxRetrofit = TdxRetrofitClient.getRetrofitInstance().create(TdxApiEndPoint::class.java)
            try {

                val response = tdxRetrofit.getAccessTokenA(
                    "client_credentials",
                    BuildConfig.TDX_CLIENT_ID,
                    BuildConfig.TDX_CLIENT_SECRET)

                if (response.isSuccessful) {
                    GlobalAccessToken.token = Result.Success(response.body()!!).data.access_token
                    GlobalAccessToken.expireTime = System.currentTimeMillis()
//                    SharePreferenceHelper.setAccessToken(context, GlobalAccessToken.token)
//                    SharePreferenceHelper.setAccessTokenExpireTime(context, GlobalAccessToken.expireTime)
                } else {
//                        accessTokenResponseData.value = Result.Error(response.message())
                    Log.i("FlightInfo", "get access token failed")
                }
            } catch (e: Exception) {
//                    accessTokenResponseData.value = Result.ErrorException(e)
                Log.i("FlightInfo", "get access token exception")
            }

        }
        delay(1000)
        ///////////////////////////////////////////////////////////////////////////
        try {
            val response = repository.getAllArrivalFlightRepository("TPE")
            if (response.isSuccessful) {
                _flightInfoResponseData.value = Result.Success(response.body()!!)
            } else {
                _flightInfoResponseData.value = Result.Error(response.message())
            }
        } catch (e: Exception) {
            _flightInfoResponseData.value = Result.ErrorException(e)
        }
    }
}