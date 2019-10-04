package com.androidstudy.branch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.branch.R
import com.androidstudy.branch.ui.model.StockMessageDemo

internal class StockMessageRecyclerViewAdapter(
    private val stockMessageDemoList: List<StockMessageDemo>,
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
        return stockMessageDemoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stockMessageDemoList[position])
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stockMessage: AppCompatTextView =
            itemView.findViewById(R.id.textStockMessage)

        fun bind(stockMessageDemo: StockMessageDemo) {
            stockMessage.text = stockMessageDemo.title
        }
    }
}