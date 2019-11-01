package com.androidstudy.branch.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["timestamp"], unique = true)])
class Message(
    @PrimaryKey
    @NonNull
    var id: Int,
    var thread_id: Int,
    var user_id: String,
    var body: String,
    var timestamp: String,
    var agent_id: String
)