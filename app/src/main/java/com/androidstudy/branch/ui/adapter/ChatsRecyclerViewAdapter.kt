package com.androidstudy.branch.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.branch.R
import com.androidstudy.branch.data.model.Chat


internal class ChatsRecyclerViewAdapter(
    private val context: Context,
    private val chatList: List<Chat>
) : RecyclerView.Adapter<ChatsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatList[position], context)
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageViewUserAvatar: ImageView = itemView.findViewById(R.id.imageViewUserAvatar)
        private val textViewUserName: TextView = itemView.findViewById(R.id.textViewUserName)
        private val textViewChatStatus: TextView = itemView.findViewById(R.id.textViewChatStatus)
        private val textViewMessageBody: TextView = itemView.findViewById(R.id.textViewMessageBody)

        fun bind(chat: Chat, context: Context) {
//            imageViewUserAvatar.setImageResource(chat.image)
            textViewUserName.text = chat.user_id

            when {
                chat.status == "status_open" -> textViewChatStatus.background =
                    context.resources.getDrawable(R.drawable.bg_status_open)
                else -> textViewChatStatus.background =
                    context.resources.getDrawable(R.drawable.bg_status_closed)
            }

            when {
                chat.status == "status_open" -> textViewChatStatus.text =
                    context.getString(R.string.chat_open)
                else -> textViewChatStatus.text = context.getString(R.string.chat_closed)
            }

            textViewMessageBody.text = chat.body
        }
    }
}