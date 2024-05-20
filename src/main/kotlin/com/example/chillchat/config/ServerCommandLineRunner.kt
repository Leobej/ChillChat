package com.example.chillchat.config

import com.corundumstudio.socketio.SocketIOServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ServerCommandLineRunner : CommandLineRunner {
    @Autowired
    private val server: SocketIOServer? = null

    @Throws(Exception::class)
    override fun run(vararg args: String?) {
        server!!.start()
    }
}