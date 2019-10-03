package com.androidstudy.branch.data.repository

import com.androidstudy.branch.data.entities.MessageThread

interface ThreadRepository {

    suspend fun fetchMessageThreads(page: Int): List<MessageThread>?

}