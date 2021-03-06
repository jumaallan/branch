package com.androidstudy.branch.data.repository

import androidx.lifecycle.LiveData
import com.androidstudy.branch.data.dao.ChatDao
import com.androidstudy.branch.data.dao.ThreadDao
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.data.model.ChatMessage
import com.androidstudy.branch.data.model.ChatRequest
import com.androidstudy.branch.data.model.ChatResponse
import com.androidstudy.branch.data.model.CloseThread
import com.androidstudy.branch.data.remote.BranchAPI
import com.androidstudy.branch.util.NetworkResult
import com.androidstudy.branch.util.safeApiCall
import retrofit2.Retrofit
import java.io.IOException

@Suppress("UselessCallOnNotNull")
class ChatRepository(
    network: Retrofit,
    private var chatDao: ChatDao,
    private var threadDao: ThreadDao
) {

    private val apiService = network.create(BranchAPI::class.java)

    suspend fun sendMessage(thread_id: String, body: String) = safeApiCall(
        call = { postMessageToServer(thread_id, body) },
        errorMessage = "An error occurred"
    )

    suspend fun closeThread(thread_id: String) = safeApiCall(
        call = { closeMessageThread(thread_id) },
        errorMessage = "An error occurred"
    )

    private suspend fun postMessageToServer(
        thread_id: String,
        body: String
    ): NetworkResult<ChatResponse> {
        val messageReply = ChatRequest(
            thread_id,
            body
        )
        val response = apiService.sendMessage(messageReply)
        return when {
            response.isSuccessful -> {
                val chatResponse = response.body()
                if (chatResponse != null) {
                    saveChat(chatResponse)
                }
                NetworkResult.Success(response.body()!!)
            }
            else -> NetworkResult.Error(IOException("Could not save message"))
        }
    }

    private suspend fun closeMessageThread(
        thread_id: String
    ): NetworkResult<Void> {
        val closeThread = CloseThread(
            thread_id
        )
        val response = apiService.closeThread(closeThread)
        return when {
            response.isSuccessful -> {
                updateThreadStatus(thread_id)
                NetworkResult.Success(response.body()!!)
            }
            else -> NetworkResult.Error(IOException("Could not close message thread"))
        }
    }

    private fun updateThreadStatus(threadId: String) {
        threadDao.closeMessageThread("status_closed", threadId.toInt())
    }

    private fun saveChat(chat: ChatResponse) {
        val agentId: String = if (chat.agent_id.isNullOrEmpty()) {
            "0"
        } else {
            chat.agent_id
        }
        val messageThread = MessageThread(
            0,
            chat.thread_id,
            chat.user_id,
            chat.body,
            chat.status,
            agentId,
            chat.timestamp
        )
        threadDao.insert(messageThread)
    }

    fun fetchMessagesPerThread(thread_id: String): LiveData<List<ChatMessage>> {
        return chatDao.fetchMessagesPerThread(thread_id)
    }
}


