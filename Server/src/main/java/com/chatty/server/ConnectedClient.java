package com.chatty.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ConnectedClient {
    private InetSocketAddress socketAddress;
    private LocalDateTime lastHeartbeat;
    /**
     * The length of time (in seconds) before a client is marked as timed out.
     */
    public static int CLIENT_TIMEOUT = 60;

    public ConnectedClient(InetSocketAddress socketAddress) {
        this.socketAddress = socketAddress;
        heartbeat();
    }

    public void heartbeat() {
        lastHeartbeat = LocalDateTime.now();
    }

    public boolean isExpired() {
        long difference = ChronoUnit.SECONDS.between(LocalDateTime.now(), lastHeartbeat);
        return difference > CLIENT_TIMEOUT;
    }

    public InetSocketAddress getSocketAddress() { return socketAddress; }

    public InetAddress getIpAddress() {
        return socketAddress.getAddress();
    }

    public int getPort() {
        return socketAddress.getPort();
    }
}
