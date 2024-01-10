package com.joe.flightinfo.api.model

data class AirPortFlyInfoModel (
    val flyType: String = "",
    val airlineID: String = "",
    val airline:  String = "",
    val flightNumber:  String = "",
    val departureAirportID:  String = "",
    val departureAirport:  String = "",
    val arrivalAirportID:  String = "",
    val arrivalAirport:  String = "",
    val scheduleTime:  String = "",
    val actualTime:  String = "",
    val estimatedTime:  String = "",
    val remark:  String = "",
    val terminal:  String = "",
    val gate:  String = "",
    val updateTime:  String = ""
)
