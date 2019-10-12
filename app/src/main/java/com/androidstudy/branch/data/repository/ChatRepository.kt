package com.androidstudy.branch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidstudy.branch.data.dao.ChatDao
import com.androidstudy.branch.data.entities.Message
import retrofit2.Retrofit

class ChatRepository(retrofit: Retrofit, chatDao: ChatDao) {

    private var dao = chatDao
    private var network = retrofit

    fun fetchMessagesPerThread(thread_id: String): LiveData<PagedList<Message>> {
        return LivePagedListBuilder(dao.fetchMessagesPerThread(thread_id), 50).build()
    }
}
