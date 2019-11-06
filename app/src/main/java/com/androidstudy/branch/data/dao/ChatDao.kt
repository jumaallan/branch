package com.androidstudy.branch.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.androidstudy.branch.data.model.ChatMessage

@Dao
interface ChatDao {

    @Query("")
    fun insertMessage()

    @Query("SELECT a.id, a.thread_id, a.user_id, a.body, a.timestamp, a.agent_id FROM MessageThread a WHERE thread_id =:thread_id GROUP BY body")
    fun fetchMessagesPerThread(thread_id: String): LiveData<List<ChatMessage>>
}