package com.androidstudy.branch.data.repository

import com.androidstudy.branch.data.dao.ChatDao
import retrofit2.Retrofit

class ChatRepository(retrofit: Retrofit, chatDao: ChatDao) {

    private var dao = chatDao
    private var network = retrofit
}
