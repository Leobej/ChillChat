package com.example.chillchat.config

import com.corundumstudio.socketio.SocketIOServer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SocketIOConfig {
    @Value("\${socket-server.host}")
    private val host: String? = null

    @Value("\${socket-server.port}")
    private val port: Int? = null

    @Bean
    fun socketIOServer(): SocketIOServer {
        val config = com.corundumstudio.socketio.Configuration()
        config.hostname = host
        config.port = port!!
        return SocketIOServer(config)
    }
}