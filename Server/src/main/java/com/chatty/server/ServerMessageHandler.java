package com.chatty.server;

import com.chatty.messages.ChatMessage;
import com.chatty.messages.Heartbeat;
import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;
import com.chatty.util.Debug;

public class ServerMessageHandler implements Runnable{
    private NetworkHandler handler;
    private ConnectedClientList clients;

    public ServerMessageHandler(NetworkHandler handler) {
        this.handler = handler;

        // Setup Connected Clients
        clients = new ConnectedClientList();
    }

    @Override
    public void run() {
        while(true) {
            // TODO: This is checking the client timeout way
            // too often. We should probably slow this down.
            clients.updateClients();
            Message message = handler.getMessage();
            if(message != null) {
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
        switch(message.getType()) {
            case HEARTBEAT:
                handleHeartbeat((Heartbeat)message);
                break;
            case CHAT_MESSAGE:
                handleChatMessage((ChatMessage)message);
                break;
        }
    }

    public void handleHeartbeat(Heartbeat m) {
        clients.heartbeatClient(m.ipAddress, m.port);
    }

    public void handleChatMessage(ChatMessage m) {
        clients.forwardToAll(m);
    }
}
