package com.androidstudy.branch.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.androidstudy.branch.data.entities.MessageThread

class ThreadDiffUtil : DiffUtil.ItemCallback<MessageThread>() {

    override fun areItemsTheSame(oldItem: MessageThread, newItem: MessageThread): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MessageThread, newItem: MessageThread): Boolean {
        return oldItem.thread_id == newItem.thread_id
    }
}