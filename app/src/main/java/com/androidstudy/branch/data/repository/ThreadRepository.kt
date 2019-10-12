package com.androidstudy.branch.data.repository

import com.androidstudy.branch.data.dao.ThreadDao
import retrofit2.Retrofit

class ThreadRepository(retrofit: Retrofit, threadDao: ThreadDao) {

    private var dao = threadDao
    private var network = retrofit
}
