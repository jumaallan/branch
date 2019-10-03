package com.androidstudy.branch.ui.adapter

import androidx.paging.DataSource
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.data.repository.ThreadRepository

class ThreadDataSourceFactory(private val threadRepository: ThreadRepository) :

    DataSource.Factory<Int, MessageThread>() {
    override fun create(): DataSource<Int, MessageThread> {
        return ThreadDataSource(threadRepository)
    }
}