package com.androidstudy.branch.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.androidstudy.branch.data.entities.MessageThread

@Dao
interface ThreadDao : BaseDao<MessageThread> {

    @Query("SELECT * FROM MessageThread")
    fun fetchThreads(): DataSource.Factory<Int, MessageThread>

}