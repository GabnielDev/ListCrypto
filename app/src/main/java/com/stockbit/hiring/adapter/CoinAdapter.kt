package com.stockbit.hiring.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.stockbit.hiring.R
import com.stockbit.hiring.databinding.ItemTopBinding
import com.stockbit.hiring.model.Data

class CoinAdapter(
    private var listData: MutableList<Data>,
    private var onItemClickCallback: OnItemClickCallback
) : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listData: MutableList<Data>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTopBinding.bind(itemView)
        fun bind(data: Data) {
            with(binding) {
                imgCoin.load("https://www.cryptocompare.com/" + data.CoinInfo.ImageUrl) {
                    crossfade(true)
                    crossfade(1000)
                    placeholder(android.R.color.darker_gray)
                    error(R.drawable.ic_launcher_background)
                }
                txtNama.text = data.CoinInfo.Name
                txtFullname.text = data.CoinInfo.FullName
                txtPrice.text = data.DISPLAY.IDR.PRICE

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClick(data)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    interface OnItemClickCallback {
        fun onItemClick(data: Data)
    }
}