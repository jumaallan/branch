package com.androidstudy.branch.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.branch.R
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.util.Utils

internal class ThreadRecyclerViewAdapter(
    private val messageThreadList: List<MessageThread>,
    private var context: Context,
    private var listener: CustomItemClickListener
) : RecyclerView.Adapter<ThreadRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_message_thread, parent, false)
        val mViewHolder = ViewHolder(view)
        view.setOnClickListener { v -> listener.onItemClick(v, mViewHolder.position) }
        return mViewHolder
    }

    override fun getItemCount(): Int {
        return messageThreadList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messageThreadList[position], context)
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewUserName: TextView =
            itemView.findViewById(R.id.textViewUserName)
        private val textViewMessageBody: TextView =
            itemView.findViewById(R.id.textViewMessageBody)
        private val textViewTimeStamp: TextView =
            itemView.findViewById(R.id.textViewTimeStamp)
        private val textViewChatStatus: TextView = itemView.findViewById(R.id.textViewChatStatus)

        fun bind(
            messageThread: MessageThread,
            context: Context
        ) {
            textViewUserName.text = messageThread.id.toString()
            textViewMessageBody.text = messageThread.body
            textViewTimeStamp.text = Utils.getFormattedUpdateTime(messageThread.timestamp)
            when {
                messageThread.status == "status_open" -> textViewChatStatus.background =
                    context.resources.getDrawable(R.drawable.bg_status_open)
                else -> textViewChatStatus.background =
                    context.resources.getDrawable(R.drawable.bg_status_closed)
            }
            when {
                messageThread.status == "status_open" -> textViewChatStatus.text =
                    context.getString(R.string.chat_open)
                else -> textViewChatStatus.text = context.getString(R.string.chat_closed)
            }
        }
    }
}
