package com.androidstudy.branch.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class StockMessage(
    @PrimaryKey
    @NonNull
    var id: Int,
    var name: String,
    var category: String,
    var body: String
)