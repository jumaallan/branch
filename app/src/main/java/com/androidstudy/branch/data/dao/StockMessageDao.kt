package com.androidstudy.branch.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomWarnings
import com.androidstudy.branch.data.entities.StockMessage

@Dao
interface StockMessageDao : BaseDao<StockMessage> {

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM StockMessage")
    suspend fun fetchStockMessage(): List<StockMessage>
}