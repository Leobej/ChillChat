package com.example.chillchat.controller

import com.example.chillchat.entity.ChatMessage
import com.example.chillchat.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/message")
@CrossOrigin(origins = ["http://localhost:3000"])
class ChatController(private val messageService: MessageService) {

    @GetMapping("/{room}")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun getMessages(@PathVariable room: String?): ResponseEntity<List<ChatMessage?>> {
        return ResponseEntity.ok(messageService.getMessages(room!!))
    }
}