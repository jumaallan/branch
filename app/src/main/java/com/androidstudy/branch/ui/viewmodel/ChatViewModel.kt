package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidstudy.branch.data.entities.StockMessage
import com.androidstudy.branch.data.model.ChatMessage
import com.androidstudy.branch.data.repository.ChatRepository
import com.androidstudy.branch.data.repository.StockMessageRepository

class ChatViewModel(
    chatRepository: ChatRepository,
    stockMessageRepository: StockMessageRepository
) : ViewModel() {

    private var chatRepo = chatRepository
    private var stockMessageRepo = stockMessageRepository

    fun fetchMessagesPerThread(thread_id: String): LiveData<List<ChatMessage>> {
        return chatRepo.fetchMessagesPerThread(thread_id)
    }

    fun fetchStockMessages(): LiveData<List<StockMessage>> {
        return stockMessageRepo.fetchStockMessages()
    }
}
