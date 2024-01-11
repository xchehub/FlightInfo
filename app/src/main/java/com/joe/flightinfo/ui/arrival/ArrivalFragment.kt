package com.joe.flightinfo.ui.arrival

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.joe.flightinfo.BR
import com.joe.flightinfo.databinding.FragmentArrivalBinding
import com.joe.flightinfo.ui.Result

class ArrivalFragment : Fragment() {
    private lateinit var arrivalViewModel: ArrivalViewModel
    private var _binding: FragmentArrivalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArrivalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrivalViewModel = ViewModelProvider(this, ArrivalViewModelFactory())[ArrivalViewModel::class.java]

        binding.setVariable(BR.arrivalViewModel, arrivalViewModel)
        binding.executePendingBindings()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }


        initObserver()
    }

    private fun initObserver() {

        arrivalViewModel.flightInfoResponseData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    arrivalViewModel.setAdapterData(result.data)
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
}