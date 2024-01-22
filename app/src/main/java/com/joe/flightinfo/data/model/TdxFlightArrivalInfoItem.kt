package com.joe.flightinfo.data.model

data class TdxFlightArrivalInfoItem(
    val AcType: String,
    val ActualArrivalTime: String,
    val AirlineID: String,
    val ArrivalAirportID: String,
    val ArrivalRemark: String,
    val BaggageClaim: String,
    val DepartureAirportID: String,
    val EstimatedArrivalTime: String,
    val FlightDate: String,
    val FlightNumber: String,
    val Gate: String,
    val IsCargo: Boolean,
    val ScheduleArrivalTime: String,
    val Terminal: String,
    val UpdateTime: String
)