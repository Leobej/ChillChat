package com.example.chillchat.socket

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.SocketIOClient
import com.corundumstudio.socketio.SocketIOServer
import com.corundumstudio.socketio.listener.ConnectListener
import com.corundumstudio.socketio.listener.DataListener
import com.corundumstudio.socketio.listener.DisconnectListener
import com.example.chillchat.constants.Constants
import com.example.chillchat.entity.ChatMessage
import org.springframework.stereotype.Component
import java.lang.String
import java.util.stream.Collectors


@Component
class SocketModule(private val server: SocketIOServer, socketService: SocketService) {
    private lateinit var socketService: SocketService

    init {
        this.socketService = socketService
        server.addConnectListener(onConnected())
        server.addDisconnectListener(onDisconnected())
        server.addEventListener("send_message", ChatMessage::class.java, onChatReceived())
    }

    private fun onChatReceived(): DataListener<ChatMessage> {
        return DataListener<ChatMessage> { senderClient: SocketIOClient?, data: ChatMessage, ackSender: AckRequest? ->
//            log.info(data.toString())
            senderClient?.let { socketService.saveMessage(it, data) }
        }
    }

    private fun onConnected(): ConnectListener {
        return ConnectListener { client: SocketIOClient ->
//            String room = client.getHandshakeData().getSingleUrlParam("room");
//            String username = client.getHandshakeData().getSingleUrlParam("room");
            val params =
                client.handshakeData.urlParams
            val room = params["room"]!!.stream().collect(Collectors.joining())
            val username =
                params["username"]!!.stream().collect(Collectors.joining())
            client.joinRoom(room)
            socketService.saveInfoMessage(client, String.format(Constants.WELCOME_MESSAGE, username), room)
//            log.info(
//                "Socket ID[{}] - room[{}] - username [{}]  Connected to chat module through",
//                client.sessionId.toString(),
//                room,
//                username
//            )
        }
    }

    private fun onDisconnected(): DisconnectListener {
        return DisconnectListener { client: SocketIOClient ->
            val params =
                client.handshakeData.urlParams
            val room = params["room"]!!.stream().collect(Collectors.joining())
            val username =
                params["username"]!!.stream().collect(Collectors.joining())
            socketService.saveInfoMessage(client, String.format(Constants.DISCONNECT_MESSAGE, username), room)
//            log.info(
//                "Socket ID[{}] - room[{}] - username [{}]  discnnected to chat module through",
//                client.sessionId.toString(),
//                room,
//                username
//            )
        }
    }
}
