package com.joe.flightinfo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joe.flightinfo.data.FlightInfoRepository

class MainViewModelFactory : ViewModelProvider.Factory {
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            repository = FlightInfoRepository()
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}