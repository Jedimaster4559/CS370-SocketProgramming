package com.chatty.net;

import com.chatty.messages.Heartbeat;
import com.chatty.util.Debug;

class Heartbeater implements Runnable {
    private int heartbeatPort;
    private int targetPort;
    private String targetIp;
    /** The rate (in seconds) at which heartbeats should be sent */
    public static int HEARTBEAT_INTERVAL = 15;

    public Heartbeater(int heartbeatPort, int targetPort, String targetIp) {
        this.heartbeatPort = heartbeatPort;
        this.targetIp = targetIp;
        this.targetPort = targetPort;
    }

    @Override
    public void run() {
        while(true) {
            NetworkHandler.sendMessage(new Heartbeat(heartbeatPort), targetIp, targetPort);
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
