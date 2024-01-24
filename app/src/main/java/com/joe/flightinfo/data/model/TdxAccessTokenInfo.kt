package com.joe.flightinfo.data.model

data class TdxAccessTokenInfo(
    var access_token: String,
    var expires_in: Int = 0,
    var refresh_expires_in: Int,
    var token_type: String,
    var not_before_policy: Int,
    var scope: String
)