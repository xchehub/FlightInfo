package com.joe.flightinfo.data.model

data class TdxAccessTokenInfo(
    val access_token: String,
    val expires_in: Int,
//    val not-before-policy: Int,
    val refresh_expires_in: Int,
    val scope: String,
    val token_type: String
)