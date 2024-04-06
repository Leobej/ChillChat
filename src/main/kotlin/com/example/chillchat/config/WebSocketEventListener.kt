package com.example.chillchat.config

import com.example.chillchat.entity.ChatMessage
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent

@Component
class WebSocketEventListener {
    private val log = LoggerFactory.getLogger(WebSocketEventListener::class.java)
    private var messageTemplate: SimpMessageSendingOperations? = null

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionConnectedEvent) {
        var headerAccessor: StompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
        var username = headerAccessor.getSessionAttributes()?.get("username").toString()
        if (username != null) {
            log.info("User Disconnected : $username")
            var chatMessage = ChatMessage(
                content = "User Disconnected",
                sender = username,
                type = ChatMessage.MessageType.LEAVE
            )
            messageTemplate?.convertAndSend("/topic/public", chatMessage)
        }
    }
}