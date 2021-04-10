package com.chatty.client;

import com.chatty.data.Chat;
import com.chatty.data.User;
import com.chatty.messages.ChatMessage;
import com.chatty.messages.ClientConnectMessage;
import com.chatty.net.NetworkHandler;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private final Scanner systemInScanner;
    private final InetAddress ipAddress;

    public Client() {
        // Setup Colored Console
        AnsiConsole.systemInstall();

        try {
            URL whatIsMyIp = new URL("https://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatIsMyIp.openStream()));
            ipAddress = InetAddress.getByName(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Read in cmd input and relay to server
        systemInScanner = new Scanner(System.in);

        // Start networking
        int targetPort = 45558;
        InetSocketAddress serverAddress = new InetSocketAddress(getServerAddress(), targetPort);


        // Start Networking
        int localPort = NetworkHandler.getFreePort();
        NetworkHandler.sendMessage(new ClientConnectMessage(localPort), serverAddress);

        NetworkHandler networking = new NetworkHandler(localPort);
        networking.startListener();
        networking.startHeartbeatSession(new InetSocketAddress(ipAddress, localPort), serverAddress);


        // Start Message Handling
        ClientMessageHandler messageHandler = new ClientMessageHandler(networking);
        messageHandler.start();

        String username = getUserName();
        User selfUser = new User(username, Ansi.Color.GREEN);

        while (true) {
            String line = systemInScanner.nextLine();
            Chat chat = new Chat(selfUser, line);
            networking.sendMessage(new ChatMessage(chat), serverAddress);
        }
    }

    String getUserName() {
        System.out.println("Enter your user name:");
        return systemInScanner.nextLine();
    }

    InetAddress getServerAddress() {
        // Start networking
        try {
            System.out.println("Enter server ip address [localhost]:");
            String rawIp = systemInScanner.nextLine();
            return InetAddress.getByName(rawIp);
        } catch (UnknownHostException e) {
            System.out.println("Error: invalid ip address");
            return getServerAddress();
        }
    }
}
