package com.androidstudy.branch.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.androidstudy.branch.data.entities.Message
import com.androidstudy.branch.data.entities.MessageThread

@Dao
interface ChatDao : BaseDao<MessageThread> {

    @Query("SELECT * FROM MessageThread")
    fun fetchMessageThreads(): LiveData<List<MessageThread>>

    @Query("SELECT * FROM Message WHERE thread_id =:thread_id")
    fun fetchMessagesPerThread(thread_id: String): LiveData<List<Message>>

}