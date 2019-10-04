package com.androidstudy.branch.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.branch.R
import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.util.Utils
import com.androidstudy.branch.util.inflate
import kotlinx.android.synthetic.main.message_list_item.view.*

class ThreadRecyclerViewAdapter :
    PagedListAdapter<Chat, ThreadRecyclerViewAdapter.ThreadViewHolder>(
        ChatDiffUtil()
    ) {

    lateinit var onThreadClickListener: (Chat) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadViewHolder =
        ThreadViewHolder(parent.inflate(R.layout.message_list_item))

    override fun onBindViewHolder(holder: ThreadViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onThreadClickListener)
        }
    }

    inner class ThreadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: Chat,
            onThreadClickListener: (Chat) -> Unit
        ) = with(itemView) {

            textViewUserName.text = item.user_id
            when {
                item.status == "status_open" -> textViewChatStatus.background =
                    context.resources.getDrawable(R.drawable.bg_status_open)
                else -> textViewChatStatus.background =
                    context.resources.getDrawable(R.drawable.bg_status_closed)
            }
            when {
                item.status == "status_open" -> textViewChatStatus.text =
                    context.getString(R.string.chat_open)
                else -> textViewChatStatus.text = context.getString(R.string.chat_closed)
            }
            textViewMessageBody.text = item.body
            textViewTimeStamp.text = Utils.getFormattedUpdateTime(item.timestamp)

            itemView.setOnClickListener {
                onThreadClickListener(item)
            }
        }
    }
}
