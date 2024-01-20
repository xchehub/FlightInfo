package com.joe.flightinfo.helper

import android.R.layout
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.joe.flightinfo.R
import com.joe.flightinfo.databinding.DialogConfirmBinding
import com.joe.flightinfo.databinding.DialogCurrencyCalculatorBinding
import com.joe.flightinfo.databinding.DialogSingleBinding

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

//        binding.tvTitle.text = title
//        binding.tvMsg.text = content
//        if (btnText.isNotEmpty()) binding.btn.text = btnText

        ArrayAdapter.createFromResource(
            context,
            R.array.currency_calculator_spinner_items,
            R.layout.dialog_currency_calculator
            ).also {
                arrayAdapter -> arrayAdapter.setDropDownViewResource(R.layout.dialog_currency_calculator)
            binding.currencyFromSpinner.adapter = arrayAdapter
        }

        ArrayAdapter.createFromResource(
            context,
            R.array.currency_calculator_spinner_items,
            R.layout.dialog_currency_calculator
        ).also {
                arrayAdapter -> arrayAdapter.setDropDownViewResource(R.layout.dialog_currency_calculator)
            binding.currencyToSpinner.adapter = arrayAdapter
        }

        binding.currencyFromEdittext.setOnClickListener(View.OnClickListener {
            binding.currencyFromEdittext.text
        })

        binding.currencyToEdittext.setOnClickListener(View.OnClickListener {
            binding.currencyToEdittext.text
        })

        binding.okButton.setOnClickListener {
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