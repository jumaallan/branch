package com.androidstudy.branch.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.androidstudy.branch.data.entities.MessageThread

@Dao
interface ThreadDao : BaseDao<MessageThread> {

    @Query("SELECT * FROM MessageThread LIMIT 200")
    fun fetchMessageThreads(): LiveData<List<MessageThread>>

}