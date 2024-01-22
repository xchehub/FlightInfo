package com.joe.flightinfo.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joe.flightinfo.data.CurrencyInfoRepository
import com.joe.flightinfo.data.adapter.CurrencyDataAdapter
import com.joe.flightinfo.data.model.CurrenciesModel
import com.joe.flightinfo.data.model.CurrencyDisplayData
import com.joe.flightinfo.ui.Result
import kotlinx.coroutines.launch

class CurrencyViewModel (private val repository: CurrencyInfoRepository) : ViewModel() {

    private val _currencyInfoResponseData = MutableLiveData<Result<CurrenciesModel>>()
    val currencyInfoResponseData: LiveData<Result<CurrenciesModel>> =
        _currencyInfoResponseData
    private var currencyDataAdapter: CurrencyDataAdapter = CurrencyDataAdapter()

    init {
        makeApiCall()
    }

    fun getAdapter(): CurrencyDataAdapter {
        return currencyDataAdapter
    }

    fun setAdapterData(data: ArrayList<CurrencyDisplayData>) {
        currencyDataAdapter.setData(data)
        currencyDataAdapter.notifyDataSetChanged()
    }

    private fun makeApiCall(input: String? = null) = viewModelScope.launch {
        try {
            val response = repository.getAllCurrencyRepository()

            if (response.isSuccessful) {
                _currencyInfoResponseData.value = Result.Success(response.body()!!)
            } else {
                _currencyInfoResponseData.value = Result.Error(response.message())
            }
        } catch (e: Exception) {
            _currencyInfoResponseData.value = Result.ErrorException(e)
        }
    }

}