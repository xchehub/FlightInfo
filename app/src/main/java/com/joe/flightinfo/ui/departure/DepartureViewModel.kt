package com.joe.flightinfo.ui.departure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.flightinfo.data.FlightInfoRepository
import com.joe.flightinfo.data.adapter.DepartureDataAdapter
import com.joe.flightinfo.data.model.TdxFlightDepartureInfoItem
import com.joe.flightinfo.ui.Result
import kotlinx.coroutines.launch

class DepartureViewModel(private val repository: FlightInfoRepository) : ViewModel() {

    private val _flightInfoResponseData = MutableLiveData<Result<ArrayList<TdxFlightDepartureInfoItem>>>()
    val flightInfoResponseData: LiveData<Result<ArrayList<TdxFlightDepartureInfoItem>>> =
        _flightInfoResponseData

    private var dataAdapter: DepartureDataAdapter = DepartureDataAdapter()

    init {
        makeApiCall()
    }

    fun getAdapter(): DepartureDataAdapter {
        return dataAdapter
    }

    fun setAdapterData(data: ArrayList<TdxFlightDepartureInfoItem>) {
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

}