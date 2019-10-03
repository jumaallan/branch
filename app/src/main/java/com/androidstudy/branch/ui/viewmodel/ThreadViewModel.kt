package com.androidstudy.branch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.ui.adapter.ThreadDataSource
import com.androidstudy.branch.ui.adapter.ThreadDataSourceFactory

class ThreadViewModel(dataSourceFactory: ThreadDataSourceFactory) : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .build()

    val threadDataSource: ThreadDataSource = dataSourceFactory.create() as ThreadDataSource

    private val pagedList = LivePagedListBuilder(dataSourceFactory, config)
        .setInitialLoadKey(1)
        .build()

    fun fetchThreads(): LiveData<PagedList<MessageThread>> {
        return pagedList
    }

    fun invalidateDataSource() {
        threadDataSource.invalidate()
    }
}
