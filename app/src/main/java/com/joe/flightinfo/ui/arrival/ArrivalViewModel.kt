package com.joe.flightinfo.ui.arrival

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.flightinfo.data.FlightInfoRepository
import com.joe.flightinfo.data.adapter.DataAdapter
import com.joe.flightinfo.data.model.TdxFlightArrivalInfoItem
import com.joe.flightinfo.ui.Result
import kotlinx.coroutines.launch

class ArrivalViewModel(private val repository: FlightInfoRepository) : ViewModel() {

    private val _flightInfoResponseData = MutableLiveData<Result<ArrayList<TdxFlightArrivalInfoItem>>>()
    val flightInfoResponseData: LiveData<Result<ArrayList<TdxFlightArrivalInfoItem>>> =
        _flightInfoResponseData

    private var dataAdapter: DataAdapter = DataAdapter()

    init {
        makeApiCall()
    }

    fun getAdapter(): DataAdapter {
        return dataAdapter
    }

    fun setAdapterData(data: ArrayList<TdxFlightArrivalInfoItem>) {
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }

    private fun makeApiCall(input: String? = null) = viewModelScope.launch {

        try {
            val response = repository.getAllArrivalFlightRepository("TPE")
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