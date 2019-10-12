package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.data.repository.ThreadRepository

class ThreadViewModel(threadRepository: ThreadRepository) : ViewModel() {

    private var repo = threadRepository

    fun fetchThreads(): LiveData<PagedList<MessageThread>> {
        return repo.fetchThreads()
    }
}
