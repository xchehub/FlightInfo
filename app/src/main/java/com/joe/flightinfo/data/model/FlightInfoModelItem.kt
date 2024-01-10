package com.joe.flightinfo.data.model

data class FlightInfoModelItem(
    val actualTime: String,
    val airline: String,
    val airlineID: String,
    val arrivalAirport: String,
    val arrivalAirportID: String,
    val departureAirport: String,
    val departureAirportID: String,
    val estimatedTime: String,
    val flightNumber: String,
    val flyType: String,
    val gate: String,
    val remark: String,
    val scheduleTime: String,
    val terminal: String,
    val updateTime: String
)