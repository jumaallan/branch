package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidstudy.branch.data.model.Chat
import com.androidstudy.branch.ui.adapter.ChatDataSource
import com.androidstudy.branch.ui.adapter.ChatDataSourceFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ChatViewModel(dataSourceFactory: ChatDataSourceFactory) : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .build()

    val chatDataSource: ChatDataSource = dataSourceFactory.create() as ChatDataSource

    private val pagedList = LivePagedListBuilder(dataSourceFactory, config)
        .setInitialLoadKey(1)
        .build()

    fun fetchThreads(): LiveData<PagedList<Chat>> {
        return pagedList
    }

    @ExperimentalCoroutinesApi
    fun invalidateDataSource() {
        chatDataSource.invalidate()
    }
}
