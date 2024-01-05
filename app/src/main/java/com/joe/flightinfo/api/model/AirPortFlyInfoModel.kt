package com.joe.flightinfo.api.model

data class AirPortFlyInfoModel (
    val FlyType: String = "",
    val AirlineID: String = "",
    val Airline:  String = "",
    val FlightNumber:  String = "",
    val DepartureAirportID:  String = "",
    val DepartureAirport:  String = "",
    val ArrivalAirportID:  String = "",
    val ArrivalAirport:  String = "",
    val ScheduleTime:  String = "",
    val ActualTime:  String = "",
    val EstimatedTime:  String = "",
    val Remark:  String = "",
    val Terminal:  String = "",
    val Gate:  String = "",
    val UpdateTime:  String = ""
)
