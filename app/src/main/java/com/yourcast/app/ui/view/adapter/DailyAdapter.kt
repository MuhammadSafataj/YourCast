package com.yourcast.app.ui.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yourcast.app.R
import com.yourcast.app.data.model.Daily
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class DailyAdapter(private val context: Context) : RecyclerView.Adapter<DailyAdapter.ViewHolder>() {

    private var data: List<Daily> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Daily>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_daily, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date = Date(data[position].dt * 1000)
        val format = SimpleDateFormat("EEEE", Locale.US)

        val min = data[position].temp.min.roundToInt()
        val max = data[position].temp.max.roundToInt()

        val icon = data[position].weather[0].icon
        val iconUrl = "http://openweathermap.org/img/wn/$icon.png"
        Glide.with(context).load(iconUrl).into(holder.iconWeather)

        holder.textDay.text = format.format(date)
        holder.textDescription.text = data[position].weather[0].description
        "$min° / $max°".also { holder.textTemperature.text = it }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconWeather: ImageView = itemView.findViewById(R.id.icon_weather)
        val textDay: TextView = itemView.findViewById(R.id.text_day)
        val textDescription: TextView = itemView.findViewById(R.id.text_description)
        val textTemperature: TextView = itemView.findViewById(R.id.text_temperature)
    }
}