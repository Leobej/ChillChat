package com.example.chillchat.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class ChatMessage(
    @Enumerated(EnumType.STRING)
    val messageType: MessageType? = null,
    val content: String? = null,
    val room: String? = null,
    val username: String? = null
) : BaseModel()
