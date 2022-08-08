package com.yourcast.app.ui.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import com.yourcast.app.R
import com.yourcast.app.data.model.Hourly
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HourlyAdapter: Adapter<HourlyAdapter.ViewHolder>() {

    private var data: List<Hourly> = emptyList()

    fun setData(data: List<Hourly>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_hourly, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date = Date(data[position].dt * 1000)
        val format = SimpleDateFormat("hh:mm", Locale.US)

        holder.textTime.text = format.format(date)
        holder.textTemperature.text = String.format("%s°", data[position].temp.roundToInt())

        val icon = data[position].weather[0].icon
        val iconUrl = "http://openweathermap.org/img/wn/$icon.png"
        Glide.with(holder.itemView.context).load(iconUrl).into(holder.iconWeather)

        val currentDate = Date()
        if (currentDate.hours == date.hours) {
            holder.layout.setBackgroundResource(R.color.blue_500)
            holder.textTime.setTextColor(Color.WHITE)
            holder.textTemperature.setTextColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textTime: TextView = itemView.findViewById(R.id.text_time)
        val textTemperature: TextView = itemView.findViewById(R.id.text_temperature)
        val iconWeather: ImageView = itemView.findViewById(R.id.icon_weather)
        val layout: LinearLayout = itemView.findViewById(R.id.layout)
    }
}