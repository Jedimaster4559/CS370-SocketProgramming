package com.chatty.net;

import com.chatty.messages.Message;
import com.chatty.util.Debug;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkHandler {
    private MessageQueue queue;
    private int receivePort;
    private Heartbeater heartbeater;


    /**
     * Creates a new networking handler. This starts a new message queue as part of this process.
     *
     * @param receivePort The port to receive messages on
     */
    public NetworkHandler(int receivePort) {
        queue = new MessageQueue();
        this.receivePort = receivePort;
        heartbeater = null;
    }

    /**
     * Starts listening for incoming messages
     */
    public void startListener() {
        MessageReceiver r = new MessageReceiver(receivePort, queue);
        r.start();
    }

    /**
     * Gets the next message available if one has been received.
     *
     * @return The next message that was received. If there are no
     * messages available, returns null.
     */
    public Message getMessage() {
        return queue.dequeue();
    }

    /**
     * Sends a message to another system. Right now this is only
     * the local system.
     *
     * @param message The message to send
     */
    public static void sendMessage(Message message, InetSocketAddress socketAddress) {
        // Serialize Message
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(InetSocketAddress.class, new InetSocketAddressSerializer());
        Gson gson = builder.create();
        String json = gson.toJson(message);

        try {
            Socket socket = new Socket(socketAddress.getAddress(), socketAddress.getPort());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(json);
        } catch (IOException e) {
            Debug.error("An Exception Occurred: " + e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds a free port on the local system.
     *
     * @return an available port to use for listening. Returns -1 if no available ports.
     */
    public static int getFreePort() {
        try {
            ServerSocket s = new ServerSocket(0);
            int port = s.getLocalPort();
            s.close();
            return port;
        } catch (IOException e) {
            Debug.error("Somehow you have no available ports! Congratulations on this amazing feat!\n " + e.getStackTrace());
        }
        return -1;
    }

    public void startHeartbeatSession(InetSocketAddress localAddress, InetSocketAddress socketAddress) {
        if (heartbeater == null) {
            heartbeater = new Heartbeater(localAddress, socketAddress);
            heartbeater.start();
        }
    }
}
