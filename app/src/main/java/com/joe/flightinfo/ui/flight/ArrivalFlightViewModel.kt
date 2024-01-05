package com.joe.flightinfo.ui.flight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArrivalFlightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is arrival Fragment"
    }
    val text: LiveData<String> = _text
}