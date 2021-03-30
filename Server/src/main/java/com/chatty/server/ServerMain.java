package com.chatty.server;

import com.chatty.messages.ChatMessage;
import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;

public class ServerMain {
    public static void main(String[] args) {
        // Setup Networking
        NetworkHandler networking = new NetworkHandler(45557, 45558);
        networking.startListener();

        // While true, receive messages and print to terminal
        while(true) {
            Message message = networking.getMessage();
            if(message != null){
                String text = ((ChatMessage)message).getChat().getBody();
                System.out.println(text);
            }
        }
    }
}
