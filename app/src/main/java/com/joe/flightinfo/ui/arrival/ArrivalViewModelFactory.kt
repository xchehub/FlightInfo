package com.joe.flightinfo.ui.arrival

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joe.flightinfo.data.FlightInfoRepository

class ArrivalViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArrivalViewModel(
            repository = FlightInfoRepository()
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}