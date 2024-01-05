package com.joe.flightinfo.api.common

interface ApiRequest {
    fun onAPISuccess(data: Any?)
    fun onAPIFailure(e: Throwable)
}