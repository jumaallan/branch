package com.androidstudy.branch.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomWarnings
import com.androidstudy.branch.data.entities.MessageThread

@Dao
interface ThreadDao : BaseDao<MessageThread> {

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM MessageThread")
    fun fetchThreads(): List<MessageThread>
}