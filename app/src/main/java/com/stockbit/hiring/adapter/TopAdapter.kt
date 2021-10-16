package com.stockbit.hiring.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.R

class TopAdapter(val data: ArrayList<String>): RecyclerView.Adapter<TopAdapter.TopViewHolder>() {

    class TopViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtNama)

        fun bind(data: String) {
            txtName.text = data

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_top, parent, false)
        return TopViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
      holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}