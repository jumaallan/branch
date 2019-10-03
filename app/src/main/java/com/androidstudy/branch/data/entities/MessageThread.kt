package com.androidstudy.branch.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["thread_id"], unique = true)])
class MessageThread(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Long,
    var thread_id: Int,
    var user_id: String,
    var body: String,
    var status: String,
    var timestamp: String
)