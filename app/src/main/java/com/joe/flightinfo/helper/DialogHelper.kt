package com.joe.flightinfo.helper

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.joe.flightinfo.BuildConfig
import com.joe.flightinfo.R
import com.joe.flightinfo.databinding.DialogConfirmBinding
import com.joe.flightinfo.databinding.DialogCurrencyCalculatorBinding
import com.joe.flightinfo.databinding.DialogSingleBinding
import dev.jmoore.fcapi.FcApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.math.BigDecimal
import java.text.DecimalFormat


class DialogHelper {
    interface ITwoEventCallback {
        fun onCancelClicked()
        fun onOKClicked()
    }

    fun showConfirmDialog(
        context: Context,
        title: String,
        content: String,
        listener: ITwoEventCallback
    ): AlertDialog {
        val binding = DialogConfirmBinding.inflate(LayoutInflater.from(context))
        val dialog = createDialog(context, binding.root)

        binding.tvTitle.text = title
        binding.tvMsg.text = content

        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
            listener.onCancelClicked()
        }

        binding.btnConfirm.setOnClickListener {
            dialog.dismiss()
            listener.onOKClicked()
        }

        return dialog
    }

    fun showSingleButton(
        context: Context,
        title: String,
        content: String,
        btnText: String,
        listener: ITwoEventCallback
    ): AlertDialog {
        val binding = DialogSingleBinding.inflate(LayoutInflater.from(context))
        val dialog = createDialog(context, binding.root)

        binding.tvTitle.text = title
        binding.tvMsg.text = content
        if (btnText.isNotEmpty()) binding.btn.text = btnText
        binding.btn.setOnClickListener {
            dialog.dismiss()
            listener.onOKClicked()
        }

        return dialog
    }

    fun showCurrencyCalculator(
        context: Context,
        title: String,
        content: String,
        btnText: String,
        listener: ITwoEventCallback
    ): AlertDialog {

        val binding = DialogCurrencyCalculatorBinding.inflate(LayoutInflater.from(context))
        val dialog = createDialog(context, binding.root)

        val currencyItems = context.resources.getStringArray(R.array.currency_calculator_spinner_items)

        val fromSpinner = binding.currencyFromSpinner
        val fromAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, currencyItems)
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromSpinner.adapter = fromAdapter

        fromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        val toSpinner = binding.currencyToSpinner
        val toItems = context.resources.getStringArray(R.array.currency_calculator_spinner_items)
        val toAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, toItems)
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        toSpinner.adapter = toAdapter

        toSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        val api = FcApi(BuildConfig.CURRENCIES_APIKEY)
        val fromEdit = binding.currencyFromEdittext
        val toTextView = binding.currencyToTextview

        fromEdit.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                fromEdit.removeTextChangedListener(this)

                val from = fromSpinner.selectedItem.toString()
                var converted1: BigDecimal = BigDecimal(0)
                val toStr = toSpinner.selectedItem.toString()
                var sourceValue = fromEdit.text.toString()
                if (sourceValue.isEmpty()) {
                    sourceValue = "0.0"
                }

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        converted1 = api.convert(from, toStr, BigDecimal(sourceValue))
                    } catch (e: dev.jmoore.fcapi.api.InvalidCurrencyException) {
                        Log.i("flightinfo", "fcapi error")
                        Log.i("flightinfo", e.printStackTrace().toString())
                    } catch (e: IOException) {
                        Log.i("flightinfo", e.printStackTrace().toString())
                    }

                    Log.i("flightinfo", "converted1: $converted1")
                    val dec = DecimalFormat("#,###.##")
                    toTextView.setText(dec.format(converted1))
                }

                fromEdit.addTextChangedListener(this)
            }
        })

        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
            listener.onCancelClicked()
        }

        binding.btnConfirm.setOnClickListener {
            dialog.dismiss()
            listener.onOKClicked()
        }

        return dialog
    }

    private fun createDialog(context: Context, view: View): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        builder.setCancelable(false)
        return builder.create()
    }
}