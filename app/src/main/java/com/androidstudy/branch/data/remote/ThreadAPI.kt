package com.androidstudy.branch.data.remote

import com.androidstudy.branch.data.model.Chat
import retrofit2.http.GET

interface ThreadAPI {

    @GET("api/messages")
    suspend fun getMessages(): List<Chat>
}