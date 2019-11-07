package com.androidstudy.branch.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidstudy.branch.R;
import com.androidstudy.branch.data.model.ChatMessage;
import com.androidstudy.branch.util.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private List<ChatMessage> mMessageList;

    public ChatRecyclerViewAdapter(List<ChatMessage> messageList) {
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        ChatMessage message = mMessageList.get(position);
        if (message.getAgent_id().equals("0")) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = mMessageList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;
        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.textSentMessageBody);
            timeText = itemView.findViewById(R.id.textSentMessageTime);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getBody());
            timeText.setText(Utils.Companion.getFormattedUpdateTime(message.getTimestamp()));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.textMessageBody);
            timeText = itemView.findViewById(R.id.textMessageTime);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getBody());
            timeText.setText(Utils.Companion.getFormattedUpdateTime(message.getTimestamp()));
        }
    }
}
