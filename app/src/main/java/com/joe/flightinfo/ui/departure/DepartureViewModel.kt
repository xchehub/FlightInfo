package com.joe.flightinfo.ui.departure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.flightinfo.data.FlightInfoRepository
import com.joe.flightinfo.data.adapter.DataAdapter
import com.joe.flightinfo.data.model.FlightInfoModelItem
import com.joe.flightinfo.ui.Result
import kotlinx.coroutines.launch

class DepartureViewModel(private val repository: FlightInfoRepository) : ViewModel() {

    private val _flightInfoResponseData = MutableLiveData<Result<ArrayList<FlightInfoModelItem>>>()
    val flightInfoResponseData: LiveData<Result<ArrayList<FlightInfoModelItem>>> =
        _flightInfoResponseData

    private var dataAdapter: DataAdapter = DataAdapter()

    init {
        getTdxAccessToken()
        makeApiCall()
    }

    fun getAdapter(): DataAdapter {
        return dataAdapter
    }

    fun setAdapterData(data: ArrayList<FlightInfoModelItem>) {
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }

    private fun makeApiCall(input: String? = null) = viewModelScope.launch {
        try {
            val response = repository.getAllDepartureFlightRepository("TPE")
            if (response.isSuccessful) {
                _flightInfoResponseData.value = Result.Success(response.body()!!)
            } else {
                _flightInfoResponseData.value = Result.Error(response.message())
            }
        } catch (e: Exception) {
            _flightInfoResponseData.value = Result.ErrorException(e)
        }
    }

    private fun getTdxAccessToken() = viewModelScope.launch {
        try {
            val response = repository.getAllDepartureFlightRepository("TPE")
            if (response.isSuccessful) {
                _flightInfoResponseData.value = Result.Success(response.body()!!)
            } else {
                _flightInfoResponseData.value = Result.Error(response.message())
            }
        } catch (e: Exception) {
            _flightInfoResponseData.value = Result.ErrorException(e)
        }
    }

}