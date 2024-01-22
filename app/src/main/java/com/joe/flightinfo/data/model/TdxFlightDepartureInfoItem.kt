package com.joe.flightinfo.data.model

data class TdxFlightDepartureInfoItem(
    val ActualDepartureTime: String,
    val AirlineID: String,
    val ArrivalAirportID: String,
    val CheckCounter: String,
    val DepartureAirportID: String,
    val DepartureRemark: String,
    val EstimatedDepartureTime: String,
    val FlightDate: String,
    val FlightNumber: String,
    val Gate: String,
    val IsCargo: Boolean,
    val ScheduleDepartureTime: String,
    val Terminal: String,
    val UpdateTime: String
)