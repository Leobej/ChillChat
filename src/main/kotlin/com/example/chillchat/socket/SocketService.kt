package com.example.chillchat.socket

import com.corundumstudio.socketio.SocketIOClient
import com.example.chillchat.entity.ChatMessage
import com.example.chillchat.entity.MessageType
import com.example.chillchat.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class SocketService @Autowired constructor(private val messageService: MessageService) {

    fun sendSocketMessage(senderClient: SocketIOClient, message: ChatMessage?, room: String?) {
        for (client in senderClient.namespace.getRoomOperations(room).clients) {
            if (client.sessionId != senderClient.sessionId) {
                client.sendEvent(
                    "read_message",
                    message
                )
            }
        }
    }

    fun saveMessage(senderClient: SocketIOClient, message: ChatMessage) {
        message.createdDateTime = Date()
        val storedMessage: ChatMessage? = messageService?.saveMessage(message)
        sendSocketMessage(senderClient, storedMessage, message.room)
    }

    fun saveInfoMessage(senderClient: SocketIOClient, message: String?, room: String?) {
        val storedMessage: ChatMessage = messageService!!.saveMessage(
            ChatMessage(
                messageType = MessageType.SERVER, content = message, room = room
            )
        )
        storedMessage.createdDateTime = Date()
        sendSocketMessage(
            senderClient, storedMessage, room
        )
    }
}
