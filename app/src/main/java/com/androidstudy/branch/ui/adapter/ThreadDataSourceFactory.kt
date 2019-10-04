package com.androidstudy.branch.ui.adapter

import androidx.paging.DataSource
import com.androidstudy.branch.data.model.Chat

import com.androidstudy.branch.data.repository.ThreadRepository

class ThreadDataSourceFactory(private val threadRepository: ThreadRepository) :

    DataSource.Factory<Int, Chat>() {
    override fun create(): DataSource<Int, Chat> {
        return ThreadDataSource(threadRepository)
    }
}