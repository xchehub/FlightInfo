package com.joe.flightinfo.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joe.flightinfo.data.model.CurrencyDisplayData
import com.joe.flightinfo.databinding.CurrenciesItemLayoutBinding

class CurrencyDataAdapter : RecyclerView.Adapter<CurrencyDataAdapter.MyViewHolder>() {
    private var items = ArrayList<CurrencyDisplayData>()

    fun setData(data: ArrayList<CurrencyDisplayData>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CurrenciesItemLayoutBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(val binding: CurrenciesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CurrencyDisplayData) {
//            binding.flightInfoData = data
            binding.executePendingBindings()
        }

    }

}