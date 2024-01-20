package com.joe.flightinfo.ui.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joe.flightinfo.data.CurrencyInfoRepository

class CurrencyViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyViewModel(
            repository = CurrencyInfoRepository()
        ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}