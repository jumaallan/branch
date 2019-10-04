package com.androidstudy.branch.data.repository

import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.data.remote.ChatAPI
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(private val chatAPI: ChatAPI) : BaseRepository(),
    ChatRepository {

    override suspend fun fetchMessageThreads(page: Int): List<Chat>? {
        return call(
            { chatAPI.getMessages().map { it.toChat() } },
            "Error Getting Messages"
        )
    }
}