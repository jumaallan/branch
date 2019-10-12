package com.androidstudy.branch.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.androidstudy.branch.data.entities.Message

@Dao
interface ChatDao : BaseDao<Message> {

    @Query("SELECT * FROM Message WHERE thread_id =:thread_id")
    fun fetchMessagesPerThread(thread_id: String): DataSource.Factory<Int, Message>

}