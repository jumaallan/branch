package com.androidstudy.branch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidstudy.branch.data.dao.ThreadDao
import com.androidstudy.branch.data.entities.MessageThread
import retrofit2.Retrofit

class ThreadRepository(retrofit: Retrofit, threadDao: ThreadDao) {

    private var dao = threadDao
    private var network = retrofit

    fun fetchThreads(): LiveData<PagedList<MessageThread>> {
        return LivePagedListBuilder(dao.fetchThreads(), 50).build()
    }
}
