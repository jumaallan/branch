package com.androidstudy.branch.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.androidstudy.branch.data.entities.Thread

interface ThreadDao : BaseDao<Thread> {

    @Query("SELECT * FROM Thread LIMIT 200")
    fun fetchCustomers(): LiveData<List<Thread>>
}