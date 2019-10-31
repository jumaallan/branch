package com.androidstudy.branch.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.androidstudy.branch.data.dao.ThreadDao
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.data.remote.BranchAPI
import com.androidstudy.branch.util.NetworkResult
import com.androidstudy.branch.util.safeApiCall
import retrofit2.Retrofit
import java.io.IOException

class ThreadRepository(
    retrofit: Retrofit,
    threadDao: ThreadDao
) {
    private var dao = threadDao
    private var network = retrofit
    private val apiService = network.create(BranchAPI::class.java)

    suspend fun fetchMessageThreads() = safeApiCall(
        call = { getThreads() },
        errorMessage = "An error occurred"
    )

    private suspend fun getThreads(): NetworkResult<List<Chat>> {
        val response = apiService.getMessages()
        return when {
            response.isSuccessful -> {
                val threadsResponse = response.body()
                if (threadsResponse != null) {
                    saveThreads(threadsResponse)
                }
                NetworkResult.Success(response.body()!!)
            }
            else -> NetworkResult.Error(IOException("Could not get message threads"))
        }
    }

    fun fetchThreads(): LiveData<List<MessageThread>> = liveData {
        emit(dao.fetchThreads())
    }

    private suspend fun saveThreads(chatList: List<Chat>) {
        dao.insert(chatList)
    }
}
