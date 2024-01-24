package com.joe.flightinfo.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joe.flightinfo.data.model.TdxFlightDepartureInfoItem
import com.joe.flightinfo.databinding.FlightDepartureInfoItemLayoutBinding

class DepartureDataAdapter : RecyclerView.Adapter<DepartureDataAdapter.MyViewHolder>() {
    private var items = ArrayList<TdxFlightDepartureInfoItem>()

    fun setData(data: ArrayList<TdxFlightDepartureInfoItem>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FlightDepartureInfoItemLayoutBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(val binding: FlightDepartureInfoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TdxFlightDepartureInfoItem) {
            binding.flightDepartureInfoData = data
            binding.executePendingBindings()
        }

    }
}