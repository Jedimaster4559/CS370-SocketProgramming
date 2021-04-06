package com.chatty.client;

import com.chatty.messages.ChatMessage;
import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;
import com.chatty.util.Debug;

public class ClientMessageHandler implements Runnable {
    private NetworkHandler handler;

    public ClientMessageHandler(NetworkHandler handler) {
        this.handler = handler;
    }
    @Override
    public void run() {
        while(true) {
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
        String text = ((ChatMessage)message).getChat().getBody();
        System.out.println(text);
    }
}
