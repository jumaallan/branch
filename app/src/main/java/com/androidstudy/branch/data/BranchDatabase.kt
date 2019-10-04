package com.androidstudy.branch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidstudy.branch.data.dao.MessageDao
import com.androidstudy.branch.data.dao.ThreadDao
import com.androidstudy.branch.data.entities.Message
import com.androidstudy.branch.data.entities.MessageThread

@Database(
    entities = [
        MessageThread::class, Message::class],
    version = 1
)
abstract class BranchDatabase : RoomDatabase() {
    abstract fun threadDao(): ThreadDao
    abstract fun messageDao(): MessageDao
}