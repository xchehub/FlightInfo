package com.joe.flightinfo.ui.flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.joe.flightinfo.databinding.FragmentArrivalBinding

class ArrivalFlightFragment : Fragment() {

    private var _binding: FragmentArrivalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val arrivalFlightViewModel =
            ViewModelProvider(this).get(ArrivalFlightViewModel::class.java)

        _binding = FragmentArrivalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        arrivalFlightViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}