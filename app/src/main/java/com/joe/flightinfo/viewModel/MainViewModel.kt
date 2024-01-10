package com.joe.flightinfo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.flightinfo.api.model.AirPortFlyInfoModel
import com.joe.flightinfo.api.model.FlightResponseModel
import com.joe.flightinfo.data.FlightInfoRepository
import com.joe.flightinfo.data.adapter.DataAdapter
import kotlinx.coroutines.launch
import com.joe.flightinfo.ui.Result
class MainViewModel(private val repository: FlightInfoRepository) : ViewModel()  {

    private val _githubResponseData = MutableLiveData<Result<FlightResponseModel>>()
    val githubResponseData: LiveData<Result<FlightResponseModel>> = _githubResponseData

    var dataAdapter: DataAdapter = DataAdapter()

    init {
        makeApiCall()
    }

    fun getAdapter(): DataAdapter {
        return dataAdapter
    }

    fun setAdapterData(data: ArrayList<AirPortFlyInfoModel>) {
        dataAdapter.setData(data)
        dataAdapter.notifyDataSetChanged()
    }

    private fun makeApiCall(input: String? = null) = viewModelScope.launch {
        try {
            val response = repository.getAllDepartureFlightRepository("TPE")
            if (response.isSuccessful) {
                _githubResponseData.value = Result.Success(response.body()!!)
            }
            else{
                _githubResponseData.value = Result.Error(response.message())
            }
        }
        catch (e : Exception){
            _githubResponseData.value = Result.ErrorException(e)
        }
    }
}