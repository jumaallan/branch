package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.androidstudy.branch.data.entities.Message
import com.androidstudy.branch.data.repository.ChatRepository

class ChatViewModel(chatRepository: ChatRepository) : ViewModel() {

    private var repo = chatRepository

    fun fetchMessagesPerThread(thread_id: String): LiveData<PagedList<Message>> {
        return repo.fetchMessagesPerThread(thread_id)
    }
}
