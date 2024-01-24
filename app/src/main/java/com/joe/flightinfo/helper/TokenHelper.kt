package com.joe.flightinfo.helper

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.joe.flightinfo.BuildConfig
import com.joe.flightinfo.data.model.TdxAccessTokenInfo
import com.joe.flightinfo.network.TdxApiEndPoint
import com.joe.flightinfo.network.TdxRetrofitClient
import com.joe.flightinfo.ui.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface AccessTokenCallback {
    suspend fun successful()
    fun fail()
}
object TokenHelper {

    fun checkAccessToken(context: Context, callback: AccessTokenCallback) {

        CoroutineScope(Dispatchers.IO).launch {
            val accessTokenResponseData = MutableLiveData<Result<TdxAccessTokenInfo>>()
            val expireTime = SharePreferenceHelper.getAccessTokenExpireTime(context)
            val currentTime = System.currentTimeMillis() / 1000

            if (currentTime - expireTime < 86400) {
                callback.successful()
                return@launch
            }

            runCatching {
                val tdxRetrofit = TdxRetrofitClient.getRetrofitInstance().create(TdxApiEndPoint::class.java)
                tdxRetrofit.getAccessToken(
                    "client_credentials",
                    BuildConfig.TDX_CLIENT_ID,
                    BuildConfig.TDX_CLIENT_SECRET)
                    .await()

            }.onSuccess {
                it.body()
                SharePreferenceHelper.setAccessToken(context, Result.Success(it.body()!!).toString())
                SharePreferenceHelper.setAccessTokenExpireTime(context, System.currentTimeMillis())

                callback.successful()
            }.onFailure {

                callback.fail()
            }


        }
    }

//    fun checkAccessToken(context: Context) {
////        val oldAccessToken = SharePreferenceHelper.getAccessToken(context)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val accessTokenResponseData = MutableLiveData<Result<TdxAccessTokenInfo>>()
//            val expireTime = SharePreferenceHelper.getAccessTokenExpireTime(context)
//            val currentTime = System.currentTimeMillis() / 1000
//            val tdxRetrofit = TdxRetrofitClient.getRetrofitInstance().create(TdxApiEndPoint::class.java)
//
//            if (currentTime - expireTime < 86400) {
//                return@launch
//            }
//
//            try {
//                val response = tdxRetrofit.getAccessToken(
//                    "client_credentials",
//                    BuildConfig.TDX_CLIENT_ID,
//                    BuildConfig.TDX_CLIENT_SECRET)
//
//                if (response.isSuccessful) {
//                    accessTokenResponseData.value = Result.Success(response.body()!!)
//                    SharePreferenceHelper.setAccessToken(context, Result.Success(response.body()!!).toString())
//                    SharePreferenceHelper.setAccessTokenExpireTime(context, System.currentTimeMillis().toInt() / 1000)
//                } else {
//                    accessTokenResponseData.value = Result.Error(response.message())
//                }
//            } catch (e: Exception) {
//                accessTokenResponseData.value = Result.ErrorException(e)
//            }
//        }
//    }
}