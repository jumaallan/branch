package com.androidstudy.branch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.branch.R
import com.androidstudy.branch.ui.model.OnboardModel

internal class OnboardRecyclerViewAdapter(
    private val onboardModelList: List<OnboardModel>
) : RecyclerView.Adapter<OnboardRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_on_board, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return onboardModelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onboardModelList[position])
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: AppCompatImageView = itemView.findViewById(R.id.iv_onboard_image)
        private val title: AppCompatTextView = itemView.findViewById(R.id.tv_onboard_title)
        private val description: AppCompatTextView =
            itemView.findViewById(R.id.tv_onboard_description)

        fun bind(onboardModel: OnboardModel) {
            image.setImageResource(onboardModel.image)
            title.text = onboardModel.title
            description.text = onboardModel.description
        }
    }
}