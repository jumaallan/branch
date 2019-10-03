package com.androidstudy.branch.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.androidstudy.branch.data.entities.Message

interface MessageDao : BaseDao<Message> {

    @Query("SELECT * FROM Message LIMIT 200")
    fun fetchCustomers(): LiveData<List<Message>>

}