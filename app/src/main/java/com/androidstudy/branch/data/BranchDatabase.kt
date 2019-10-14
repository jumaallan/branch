package com.androidstudy.branch.data

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androidstudy.branch.data.dao.ChatDao
import com.androidstudy.branch.data.entities.Message
import com.androidstudy.branch.data.entities.MessageThread
import com.androidstudy.branch.data.entities.StockMessage

@androidx.room.Database(
    entities = [
        MessageThread::class,
        Message::class,
        StockMessage::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)

abstract class BranchDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao

}
