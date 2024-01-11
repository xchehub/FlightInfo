package com.joe.flightinfo.data.model

data class FlightInfoModelItem(

    // Keep the field names consistent with the case of the API response.
    val ActualTime: String,
    val Airline: String,
    val AirlineID: String,
    val ArrivalAirport: String,
    val ArrivalAirportID: String,
    val DepartureAirport: String,
    val DepartureAirportID: String,
    val EstimatedTime: String,
    val FlightNumber: String,
    val FlyType: String,
    val Gate: String,
    val Remark: String,
    val ScheduleTime: String,
    val Terminal: String,
    val UpdateTime: String
)