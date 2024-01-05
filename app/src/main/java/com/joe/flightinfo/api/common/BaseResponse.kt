package com.joe.flightinfo.api.common

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @SerializedName("FlyType")
    var flyType: String = "",

    @SerializedName("AirlineID")
    var airlineID: String = "",

    @SerializedName("Airline")
    var airline: String = "",

    @SerializedName("FlightNumber")
    var flightNumber: String = "",

    @SerializedName("DepartureAirportID")
    var departureAirportID: String = "",

    @SerializedName("DepartureAirport")
    var departureAirport: String = "",

    @SerializedName("ArrivalAirportID")
    var arrivalAirportID: String = "",

    @SerializedName("ArrivalAirport")
    var arrivalAirport: String = "",

    @SerializedName("ScheduleTime")
    var scheduleTime: String = "",

    @SerializedName("ActualTime")
    var actualTime: String = "",

    @SerializedName("EstimatedTime")
    var estimatedTime: String = "",

    @SerializedName("Remark")
    var remark: String = "",

    @SerializedName("Terminal")
    var terminal: String = "",

    @SerializedName("Gate")
    var gate: String = "",

    @SerializedName("UpdateTime")
    var updateTime: String = "",

    )
