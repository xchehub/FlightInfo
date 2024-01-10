package com.joe.flightinfo.ui.departure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joe.flightinfo.data.FlightInfoRepository

class DepartureViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DepartureViewModel(
            repository = FlightInfoRepository()
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}