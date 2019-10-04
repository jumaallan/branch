package com.androidstudy.branch.data.repository

import com.androidstudy.branch.data.model.Chat

interface ChatRepository {

    suspend fun fetchMessageThreads(page: Int): List<Chat>?

}