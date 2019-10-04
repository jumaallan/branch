package com.androidstudy.branch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidstudy.branch.data.dao.ThreadDao
import com.androidstudy.branch.data.model.Chat

@Database(
    entities = [
        Chat::class],
    version = 1
)
abstract class BranchDatabase : RoomDatabase() {
    abstract fun threadDao(): ThreadDao
}