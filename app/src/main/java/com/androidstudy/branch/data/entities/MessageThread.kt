package com.androidstudy.branch.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MessageThread(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Long,
    var thread_id: Int,
    var user_id: String,
    var body: String,
    var status: String,
    var agent_id: String,
    var timestamp: String
)