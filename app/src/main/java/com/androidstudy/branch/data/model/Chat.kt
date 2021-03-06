package com.androidstudy.branch.data.model

import androidx.annotation.Nullable

class Chat(
    var id: Int,
    var thread_id: Int,
    var user_id: String,
    var body: String,
    var timestamp: String,
    var status: String,
    @Nullable
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
            agentIdIsNull()
        )
    }

    private fun agentIdIsNull(): String {
        if (agent_id.isNullOrEmpty()) {
            return ""
        }
        return agent_id
    }
}