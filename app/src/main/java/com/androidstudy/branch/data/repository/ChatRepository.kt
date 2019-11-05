package com.androidstudy.branch.data.repository

import androidx.lifecycle.LiveData
import com.androidstudy.branch.data.dao.ChatDao
import com.androidstudy.branch.data.model.ChatMessage
import retrofit2.Retrofit

class ChatRepository(retrofit: Retrofit, chatDao: ChatDao) {

    private var dao = chatDao
    private var network = retrofit

    fun fetchMessagesPerThread(thread_id: String): LiveData<List<ChatMessage>> {
        return dao.fetchMessagesPerThread(thread_id)
    }
}


