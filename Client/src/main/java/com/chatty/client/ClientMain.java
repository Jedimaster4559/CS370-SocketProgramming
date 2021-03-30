package com.chatty.client;

import com.chatty.data.Chat;
import com.chatty.data.User;
import com.chatty.messages.ChatMessage;
import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;

import java.util.Scanner;

public class ClientMain {
    public static void main(String [] args){
        // Start networking
        NetworkHandler networking = new NetworkHandler(45558, 45557);
        networking.startListener();

        // Read in cmd input and relay to server
        Scanner in = new Scanner(System.in);
        while(true) {
            String line = in.nextLine();
            Chat chat = new Chat(new User("Jedimaster", "Green"), line);
            networking.sendMessage(new ChatMessage(chat));
        }
    }
}
