package com.chatty.client;

import com.chatty.messages.ChatMessage;
import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;
import com.chatty.util.Debug;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

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
        switch(message.getType()) {
            case CHAT:
                handleChatMessage((ChatMessage)message);
        }
    }

    public void handleChatMessage(ChatMessage m) {
        System.out.println(ansi().fg(m.getChat().getUser().getColor())
            .a("[" + m.getChat().getUser().getName() + "] ")
            .fg(Ansi.Color.DEFAULT)
            .a(m.getChat().getBody()));
    }
}
