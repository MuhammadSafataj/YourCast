package com.yourcast.app.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.yourcast.app.R
import com.yourcast.app.data.model.Direction

class SearchAdapter(private val adapterCallback: AdapterCallback) :
    Adapter<SearchAdapter.ViewHolder>() {

    private var data: List<Direction> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state = data[position].state
        val country = data[position].country
        holder.textHeader.text = data[position].name
        holder.textSubhead.text = String.format("%s, %s", state, country)

        holder.itemView.setOnClickListener {
            adapterCallback.onItemClicked(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Direction>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textHeader: TextView = itemView.findViewById(R.id.text_header)
        val textSubhead: TextView = itemView.findViewById(R.id.text_subhead)
    }

    interface AdapterCallback {
        fun onItemClicked(direction: Direction)
    }
}