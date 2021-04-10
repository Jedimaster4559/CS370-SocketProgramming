package com.chatty.net;

import com.chatty.util.Debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class MessageReceiver implements Runnable {
    private int port;
    private ServerSocket serverSocket;
    private MessageQueue queue;

    public MessageReceiver(int port, MessageQueue queue) {
        this.port = port;
        this.queue = queue;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e){
            Debug.error("An Exception Occurred: " + e.toString());
        }
    }

    public void listen() {
        Socket socket;
        try {
            socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            queue.addJson(message, socket.getInetAddress());
            socket.close();
        } catch (IOException e) {
            Debug.error("An Exception Occurred: ");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            listen();
        }
    }

    public void start() {
        Debug.log("Starting receiver thread");
        Thread t = new Thread(this);
        t.start();
    }
}
