package com.joe.flightinfo.ui.flight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DepartureFlightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is departure Fragment"
    }
    val text: LiveData<String> = _text
}