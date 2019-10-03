package com.androidstudy.branch.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["thread_id"], unique = true)])
class Thread(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Long,
    var thread_id: Int,
    var status: String
)