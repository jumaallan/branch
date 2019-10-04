package com.androidstudy.branch.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.androidstudy.branch.data.model.Chat

class ChatDiffUtil : DiffUtil.ItemCallback<Chat>() {

    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.thread_id == newItem.thread_id
    }
}