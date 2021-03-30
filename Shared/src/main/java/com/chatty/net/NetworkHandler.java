package com.chatty.net;

import com.chatty.messages.Message;
import com.chatty.util.Debug;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkHandler {
    private MessageQueue queue;
    private int receivePort;
    private int sendPort;


    /**
     * Creates a new networking handler. This starts a new message queue as part of this process.
     * @param sendPort The port to send to
     * @param receivePort The port to receive messages on
     */
    public NetworkHandler(int sendPort, int receivePort){
        queue = new MessageQueue();
        this.sendPort = sendPort;
        this.receivePort = receivePort;
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
     * @return The next message that was received. If there are no
     *         messages available, returns null.
     */
    public Message getMessage() {
        return queue.dequeue();
    }

    /**
     * Sends a message to another system. Right now this is only
     * the local system.
     * @param message The message to send
     */
    public void sendMessage(Message message) {
        Gson gson = new Gson();
        String json = gson.toJson(message);

        try {
            // TODO: Make IP configurable
            Socket socket = new Socket("127.0.0.1", sendPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(json);
        } catch (IOException e) {
            Debug.error("An Exception Occurred: " + e.getStackTrace());
        }
    }
}
