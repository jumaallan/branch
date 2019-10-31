package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.data.repository.ThreadRepository
import com.androidstudy.branch.util.NetworkResult

class ThreadViewModel(threadRepository: ThreadRepository) : ViewModel() {

    private var repo = threadRepository

    fun fetchThreads(): LiveData<PagedList<MessageThread>> {
        return repo.fetchThreads()
    }

    fun fetchMessageThreads() {
        viewModelScope.launch {
            when (val value = repo.fetchMessageThreads()) {
                is NetworkResult.Success -> charactersMediatorLiveData.postValue(value.data)
                is NetworkResult.Error -> charactersError.postValue(value.exception.message)
            }
        }
    }
}
