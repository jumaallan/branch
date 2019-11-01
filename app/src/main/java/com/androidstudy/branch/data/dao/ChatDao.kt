package com.androidstudy.branch.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.androidstudy.branch.data.entities.ChatMessage
import com.androidstudy.branch.data.entities.StockMessage

@Dao
interface ChatDao : BaseDao<ChatMessage> {

    @Query("SELECT * FROM ChatMessage WHERE thread_id =:thread_id")
    fun fetchMessagesPerThread(thread_id: String): LiveData<List<ChatMessage>>

    @Query("SELECT * FROM StockMessage")
    fun fetchStockMessages(): LiveData<List<StockMessage>>

}