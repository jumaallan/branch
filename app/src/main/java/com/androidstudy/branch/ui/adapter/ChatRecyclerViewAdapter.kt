package com.androidstudy.branch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.branch.R
import com.androidstudy.branch.data.model.ChatMessage
import com.androidstudy.branch.util.Utils.Companion.getFormattedUpdateTime

class ChatRecyclerViewAdapter(private val mMessageList: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return mMessageList.size
    }

    // Determines the appropriate ViewType according to the sender of the message.
    override fun getItemViewType(position: Int): Int {
        val message = mMessageList[position]
        return if (message.agent_id == "0") { // If the current user is the sender of the message
            VIEW_TYPE_MESSAGE_SENT
        } else { // If some other user sent the message
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_message_sent, parent, false)
            return SentMessageHolder(view)
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_message_received, parent, false)
            return ReceivedMessageHolder(view)
        }
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_message_sent, parent, false)
        return SentMessageHolder(view)
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = mMessageList[position]
        when (holder.itemViewType) {
            VIEW_TYPE_MESSAGE_SENT -> (holder as SentMessageHolder?)!!.bind(
                message
            )
            VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedMessageHolder?)!!.bind(
                message
            )
        }
    }

    private inner class SentMessageHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageText: TextView
        var timeText: TextView
        fun bind(message: ChatMessage) {
            messageText.text = message.body
            timeText.text = getFormattedUpdateTime(
                message.timestamp
            )
        }

        init {
            messageText = itemView.findViewById(R.id.textSentMessageBody)
            timeText = itemView.findViewById(R.id.textSentMessageTime)
        }
    }

    private inner class ReceivedMessageHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var messageText: TextView
        var timeText: TextView
        var nameText: TextView? = null
        fun bind(message: ChatMessage) {
            messageText.text = message.body
            timeText.text = getFormattedUpdateTime(
                message.timestamp
            )
        }

        init {
            messageText = itemView.findViewById(R.id.textMessageBody)
            timeText = itemView.findViewById(R.id.textMessageTime)
        }
    }

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }

}