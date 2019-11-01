package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidstudy.branch.data.entities.ChatMessage
import com.androidstudy.branch.data.entities.StockMessage
import com.androidstudy.branch.data.repository.ChatRepository

class ChatViewModel(chatRepository: ChatRepository) : ViewModel() {

    private var repo = chatRepository

    fun fetchMessagesPerThread(thread_id: String): LiveData<List<ChatMessage>> {
        return repo.fetchMessagesPerThread(thread_id)
    }

    fun fetchStockMessages(): LiveData<List<StockMessage>> {
        return repo.fetchStockMessages()
    }
}
