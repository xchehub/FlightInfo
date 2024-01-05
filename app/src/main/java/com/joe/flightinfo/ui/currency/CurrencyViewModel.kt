package com.joe.flightinfo.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrencyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is currency Fragment"
    }
    val text: LiveData<String> = _text
}