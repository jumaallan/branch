package com.androidstudy.branch.data.repository

import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.data.remote.ThreadAPI
import javax.inject.Inject

class ThreadRepositoryImpl @Inject constructor(private val threadAPI: ThreadAPI) : BaseRepository(),
    ThreadRepository {

    override suspend fun fetchMessageThreads(page: Int): List<Chat>? {
        return call(
            { threadAPI.getMessages().map { it.toChat() } },
            "Error Getting Messages"
        )
    }
}