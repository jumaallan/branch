package com.androidstudy.branch.data.entities

import androidx.room.Entity

@Entity
class Thread(
    var id: Int,
    var thread_id: Int,
    var status: String
)