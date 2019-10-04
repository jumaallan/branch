package com.androidstudy.branch.ui.adapter

import androidx.paging.DataSource
import com.androidstudy.branch.data.model.Chat

import com.androidstudy.branch.data.repository.ChatRepository

class ChatDataSourceFactory(private val chatRepository: ChatRepository) :

    DataSource.Factory<Int, Chat>() {
    override fun create(): DataSource<Int, Chat> {
        return ChatDataSource(chatRepository)
    }
}