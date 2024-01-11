package com.joe.flightinfo.ui.departure

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
import com.joe.flightinfo.databinding.FragmentDepartureBinding
import com.joe.flightinfo.ui.Result

//
//class DepartureFragment : Fragment() {
//
//    private var _binding: FragmentDepartureBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val galleryViewModel =
//            ViewModelProvider(this)[DepartureViewModel::class.java]
//
//        _binding = FragmentDepartureBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}


class DepartureFragment : Fragment() {
    private lateinit var departualViewModel: DepartureViewModel
    private var _binding: FragmentDepartureBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepartureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        departualViewModel = ViewModelProvider(this, DepartureViewModelFactory())[DepartureViewModel::class.java]

        binding.setVariable(BR.departureViewModel, departualViewModel)
        binding.executePendingBindings()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }

        initObserver()
    }

    private fun initObserver() {

        departualViewModel.flightInfoResponseData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    departualViewModel.setAdapterData(result.data)
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