package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.data.repository.ThreadRepository
import com.androidstudy.branch.util.NetworkResult
import com.androidstudy.branch.util.livedata.NonNullMediatorLiveData
import kotlinx.coroutines.launch

class ThreadViewModel(threadRepository: ThreadRepository) : ViewModel() {

    private var repo = threadRepository
    private val threadMediatorLiveData = NonNullMediatorLiveData<List<Chat>>()
    private val threadError = NonNullMediatorLiveData<String>()

    fun fetchThreads(): LiveData<List<MessageThread>> {
        return repo.fetchThreads()
    }

    fun getMessageThreads() {
        viewModelScope.launch {
            when (val value = repo.fetchMessageThreads()) {
                is NetworkResult.Success -> threadMediatorLiveData.postValue(value.data)
                is NetworkResult.Error -> threadError.postValue(value.exception.message)
            }
        }
    }

    fun getThreadsResponse(): LiveData<List<Chat>> = threadMediatorLiveData

    fun getThreadsError(): LiveData<String> = threadError
}
