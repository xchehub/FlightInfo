package com.joe.flightinfo.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joe.flightinfo.data.model.FlightInfoModelItem
import com.joe.flightinfo.databinding.FlightInfoItemLayoutBinding

class DataAdapter : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {
    private var items = ArrayList<FlightInfoModelItem>()

    fun setData(data: ArrayList<FlightInfoModelItem>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FlightInfoItemLayoutBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(val binding: FlightInfoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: FlightInfoModelItem) {
            binding.flightInfoData = data
            binding.executePendingBindings()
        }

    }
}