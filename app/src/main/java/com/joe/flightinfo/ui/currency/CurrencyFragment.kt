package com.joe.flightinfo.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.joe.flightinfo.BR
import com.joe.flightinfo.data.model.CurrenciesModel
import com.joe.flightinfo.data.model.CurrencyDisplayData
import com.joe.flightinfo.databinding.FragmentArrivalBinding
import com.joe.flightinfo.databinding.FragmentCurrencyBinding
import com.joe.flightinfo.helper.SharePreferenceHelper
import com.joe.flightinfo.ui.Result
import com.joe.flightinfo.ui.arrival.ArrivalViewModel
import com.joe.flightinfo.ui.arrival.ArrivalViewModelFactory

class CurrencyFragment : Fragment() {

    private lateinit var currencyViewModel: CurrencyViewModel
    private var _binding: FragmentCurrencyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyViewModel = ViewModelProvider(this, CurrencyViewModelFactory())[CurrencyViewModel::class.java]

        binding.setVariable(BR.currencyViewModel, currencyViewModel)
        binding.executePendingBindings()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }

        initObserver()
    }

    private fun initObserver() {

        currencyViewModel.currencyInfoResponseData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    binding.progressbar.visibility = View.INVISIBLE

//                    currencyViewModel.setAdapterData(result.data)
                    currencyViewModel.setAdapterData(convertToDisplayData(result.data))

                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), "Error Fetching Data", Toast.LENGTH_LONG).show()
                }
                is Result.ErrorException -> {
                    Toast.makeText(requireContext(), "Exception" , Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun convertToDisplayData(result: CurrenciesModel): ArrayList<CurrencyDisplayData> {

        var displayData = ArrayList<CurrencyDisplayData>()
        displayData.add(CurrencyDisplayData("CAD", result.data.CAD))
        displayData.add(CurrencyDisplayData("EUR", result.data.EUR))
        displayData.add(CurrencyDisplayData("CNY", result.data.CNY))
        displayData.add(CurrencyDisplayData("HKD", result.data.HKD))
        displayData.add(CurrencyDisplayData("JPY", result.data.JPY))

        SharePreferenceHelper.setCADRate(requireContext(), result.data.CAD.toString())
        SharePreferenceHelper.setEURRate(requireContext(), result.data.EUR.toString())
        SharePreferenceHelper.setCNYRate(requireContext(), result.data.CNY.toString())
        SharePreferenceHelper.setHKDRate(requireContext(), result.data.HKD.toString())
        SharePreferenceHelper.setJPYRate(requireContext(), result.data.JPY.toString())

        return displayData

    }
}