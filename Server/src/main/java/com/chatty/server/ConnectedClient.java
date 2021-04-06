package com.chatty.server;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ConnectedClient {
    private String ipAddress;
    private int port;
    private LocalDateTime lastHeartbeat;
    /** The length of time (in seconds) before a client is marked as timed out. */
    public static int CLIENT_TIMEOUT = 60;

    public ConnectedClient(String ipAddress, int port){
        this.ipAddress = ipAddress;
        this.port = port;
        heartbeat();
    }

    public void heartbeat() {
        lastHeartbeat = LocalDateTime.now();
    }

    public boolean isExpired() {
        long difference = ChronoUnit.SECONDS.between(LocalDateTime.now(), lastHeartbeat);
        return difference > CLIENT_TIMEOUT;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }
}
