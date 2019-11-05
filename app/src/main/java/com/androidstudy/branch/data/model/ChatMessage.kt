package com.androidstudy.branch.data.model

class ChatMessage(
    var id: Int,
    var thread_id: Int,
    var user_id: String,
    var body: String,
    var timestamp: String,
    var agent_id: String
)