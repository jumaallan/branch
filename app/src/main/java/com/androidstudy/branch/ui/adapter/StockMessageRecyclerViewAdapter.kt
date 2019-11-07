package com.androidstudy.branch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.branch.R
import com.androidstudy.branch.data.entities.StockMessage

@Suppress("DEPRECATION")
internal class StockMessageRecyclerViewAdapter(
    private val stockMessageList: List<StockMessage>,
    private var listener: CustomItemClickListener
) : RecyclerView.Adapter<StockMessageRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_stock_message, parent, false)
        val mViewHolder = ViewHolder(view)
        view.setOnClickListener { v -> listener.onItemClick(v, mViewHolder.position) }
        return mViewHolder
    }

    override fun getItemCount(): Int {
        return stockMessageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stockMessageList[position])
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textStockMessage: AppCompatTextView =
            itemView.findViewById(R.id.textStockMessage)

        fun bind(stockMessage: StockMessage) {
            textStockMessage.text = stockMessage.name
        }
    }
}