package com.yourcast.app.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yourcast.app.R
import com.yourcast.app.data.model.City
import kotlin.math.roundToInt

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    private var data: List<City> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<City>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_city, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temperature = data[position].current.temp.roundToInt()
        holder.textCityName.text = data[position].name
        holder.textTemperature.text = String.format("%s°", temperature)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCityName: TextView = itemView.findViewById(R.id.text_city_name)
        val textTemperature: TextView = itemView.findViewById(R.id.text_temperature)
    }
}