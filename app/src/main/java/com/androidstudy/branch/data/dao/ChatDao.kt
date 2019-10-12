package com.androidstudy.branch.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.androidstudy.branch.data.entities.Message
import com.androidstudy.branch.data.entities.StockMessage

@Dao
interface ChatDao : BaseDao<Message> {

    @Query("SELECT * FROM Message WHERE thread_id =:thread_id")
    fun fetchMessagesPerThread(thread_id: String): LiveData<List<Message>>

    @Query("SELECT * FROM StockMessage")
    fun fetchStockMessages(): LiveData<List<StockMessage>>

}