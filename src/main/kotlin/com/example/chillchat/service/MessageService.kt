package com.example.chillchat.service

import com.example.chillchat.entity.ChatMessage
import com.example.chillchat.repository.MessageRepository
import org.springframework.stereotype.Service


@Service
class MessageService {
    private val messageRepository: MessageRepository? = null
    fun getMessages(room: String): List<ChatMessage?>? {
        return messageRepository?.findAllByRoom(room)
    }

    fun saveMessage(message: ChatMessage): ChatMessage {
        return messageRepository?.save(message) ?: message
    }
}

