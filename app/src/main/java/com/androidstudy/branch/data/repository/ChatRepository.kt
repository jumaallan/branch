package com.androidstudy.branch.data.repository

import androidx.lifecycle.LiveData
import com.androidstudy.branch.data.dao.ChatDao
import com.androidstudy.branch.data.entities.ChatMessage
import com.androidstudy.branch.data.entities.StockMessage
import retrofit2.Retrofit

class ChatRepository(retrofit: Retrofit, chatDao: ChatDao) {

    private var dao = chatDao
    private var network = retrofit

    fun fetchMessagesPerThread(thread_id: String): LiveData<List<ChatMessage>> {
        return dao.fetchMessagesPerThread(thread_id)
    }

    fun fetchStockMessages(): LiveData<List<StockMessage>> {
        return dao.fetchStockMessages()
    }
}


