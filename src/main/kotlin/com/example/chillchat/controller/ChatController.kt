package com.example.chillchat.controller

import com.example.chillchat.entity.ChatMessage
import com.example.chillchat.service.MessageService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.RestController


@RestController
class ChatController {

    private val messageService: MessageService? = null

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    fun sendMessage(@Payload chatMessage: ChatMessage): ChatMessage {
        return chatMessage
    }
}