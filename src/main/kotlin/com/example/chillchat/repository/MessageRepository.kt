package com.example.chillchat.repository

import com.example.chillchat.entity.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MessageRepository : JpaRepository<ChatMessage?, Long?> {
    fun findAllByRoom(room: String?): List<ChatMessage?>?
}
