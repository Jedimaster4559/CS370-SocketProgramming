package com.chatty.server;

import com.chatty.messages.ChatMessage;
import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;

public class ServerMain {
    public static void main(String[] args) {
        // Setup Networking
        NetworkHandler networking = new NetworkHandler(45558);
        networking.startListener();

        // Start Message Handler
        ServerMessageHandler handler = new ServerMessageHandler(networking);
        handler.start();

    }
}
