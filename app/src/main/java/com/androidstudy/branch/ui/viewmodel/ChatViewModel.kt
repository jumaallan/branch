package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidstudy.branch.data.entities.StockMessage
import com.androidstudy.branch.data.model.ChatMessage
import com.androidstudy.branch.data.model.ChatResponse
import com.androidstudy.branch.data.repository.ChatRepository
import com.androidstudy.branch.data.repository.StockMessageRepository
import com.androidstudy.branch.util.NetworkResult
import com.androidstudy.branch.util.livedata.NonNullMediatorLiveData
import kotlinx.coroutines.launch

class ChatViewModel(
    chatRepository: ChatRepository,
    stockMessageRepository: StockMessageRepository
) : ViewModel() {

    private var chatRepo = chatRepository
    private var stockMessageRepo = stockMessageRepository

    private val chatMediatorLiveData = NonNullMediatorLiveData<ChatResponse>()
    private val chatError = NonNullMediatorLiveData<String>()

    fun sendMessage(thread_id: String, body: String) {
        viewModelScope.launch {
            when (val value = chatRepo.sendMessage(thread_id, body)) {
                is NetworkResult.Success -> chatMediatorLiveData.postValue(value.data)
                is NetworkResult.Error -> chatError.postValue(value.exception.message)
            }
        }
    }

    fun getChatResponse(): LiveData<ChatResponse> = chatMediatorLiveData

    fun getChatError(): LiveData<String> = chatError

    fun fetchMessagesPerThread(thread_id: String): LiveData<List<ChatMessage>> {
        return chatRepo.fetchMessagesPerThread(thread_id)
    }

    fun fetchStockMessages(): LiveData<List<StockMessage>> {
        return stockMessageRepo.fetchStockMessages()
    }
}
