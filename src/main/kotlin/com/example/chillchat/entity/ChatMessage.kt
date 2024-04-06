package com.example.chillchat.entity

class ChatMessage(
    var content: String,
    var sender: String,
    var type: MessageType
) {
    enum class MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}