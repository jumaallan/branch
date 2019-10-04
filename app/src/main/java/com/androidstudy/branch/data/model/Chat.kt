package com.androidstudy.branch.data.model

class Chat(
    var id: Int,
    var thread_id: Int,
    var user_id: String,
    var body: String,
    var timestamp: String,
    var status: String,
    var agent_id: String
) {

    fun toChat(): Chat {
        return Chat(
            id,
            thread_id,
            user_id,
            body,
            timestamp,
            status,
            agent_id
        )
    }
}