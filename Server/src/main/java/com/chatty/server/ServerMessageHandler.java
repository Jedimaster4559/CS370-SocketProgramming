package com.chatty.server;

import com.chatty.messages.ChatMessage;
import com.chatty.messages.ClientConnectMessage;
import com.chatty.messages.HeartbeatMessage;
import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;
import com.chatty.util.Debug;

import java.net.InetSocketAddress;

public class ServerMessageHandler implements Runnable {
    private NetworkHandler handler;
    private ConnectedClientList clients;

    public ServerMessageHandler(NetworkHandler handler) {
        this.handler = handler;

        // Setup Connected Clients
        clients = new ConnectedClientList();
    }

    @Override
    public void run() {
        while (true) {
            // TODO: This is checking the client timeout way
            // too often. We should probably slow this down.
            clients.updateClients();
            Message message = handler.getMessage();
            if (message != null) {
                handle(message);
            }
        }
    }

    public void start() {
        Debug.log("Starting message processor thread");
        Thread t = new Thread(this);
        t.start();
    }

    public void handle(Message message) {
        switch (message.getType()) {
            case HEARTBEAT:
                handleHeartbeat((HeartbeatMessage) message);
                break;
            case CHAT:
                handleChatMessage((ChatMessage) message);
                break;
            case CLIENT_CONNECT:
                handleClientConnectMessage((ClientConnectMessage) message);
        }
    }

    public void handleHeartbeat(HeartbeatMessage m) {
        clients.heartbeatClient(m.getSource());
    }

    public void handleChatMessage(ChatMessage m) {
        clients.forwardToAll(m);
    }

    public void handleClientConnectMessage(ClientConnectMessage m) {
        clients.addClient(new ConnectedClient(new InetSocketAddress(m.getSource(), m.getReceivePort())));
    }
}
