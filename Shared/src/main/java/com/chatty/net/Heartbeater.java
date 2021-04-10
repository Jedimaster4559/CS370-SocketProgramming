package com.chatty.net;

import com.chatty.messages.HeartbeatMessage;
import com.chatty.util.Debug;

import java.net.InetAddress;
import java.net.InetSocketAddress;

class Heartbeater implements Runnable {
    private InetSocketAddress localSocket;
    private InetSocketAddress serverSocket;
    /** The rate (in seconds) at which heartbeats should be sent */
    public static int HEARTBEAT_INTERVAL = 15;

    public Heartbeater(InetSocketAddress localSocket, InetSocketAddress serverSocket) {
        this.localSocket = localSocket;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while(true) {
            NetworkHandler.sendMessage(new HeartbeatMessage(localSocket), serverSocket);
            try {
                Thread.sleep(HEARTBEAT_INTERVAL * 1000);
            } catch (InterruptedException e) {
                Debug.error("Thread Interrupted: " + e.getStackTrace());
            }
        }
    }

    public void start() {
        Debug.log("Starting heartbeat thread");
        Thread t = new Thread(this);
        t.start();
    }
}
