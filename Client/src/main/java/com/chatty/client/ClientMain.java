package com.chatty.client;

import com.chatty.data.Chat;
import com.chatty.data.User;
import com.chatty.messages.ChatMessage;
import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

public class ClientMain {
    public static void main(String [] args){
        // Setup Colored Console
        AnsiConsole.systemInstall();

        // Start networking
        String ip = "127.0.0.1";
        int targetPort = 45558;

        // Start Networking
        NetworkHandler networking = new NetworkHandler(NetworkHandler.getFreePort());
        networking.startListener();
        networking.startHeartbeatSession(ip, targetPort);

        // Start Message Handling
        ClientMessageHandler messageHandler = new ClientMessageHandler(networking);
        messageHandler.start();

        // Read in cmd input and relay to server
        Scanner in = new Scanner(System.in);
        while(true) {
            String line = in.nextLine();
            Chat chat = new Chat(new User("Jedimaster", Ansi.Color.GREEN), line);
            networking.sendMessage(new ChatMessage(chat), ip, targetPort);
        }
    }
}
