package com.chatty.server;

import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ConnectedClientList {
    private List<ConnectedClient> clients = new ArrayList<>();

    public void heartbeatClient(InetAddress socketAddress) {
        if (null == socketAddress) {
            throw new IllegalArgumentException("socketAddress was null");
        }
        for (ConnectedClient c : clients) {
            if (c.getSocketAddress().equals(socketAddress)) {
                c.heartbeat();
                return;
            }
        }
    }

    public void addClient(ConnectedClient c) {
        clients.add(c);
    }

    public void updateClients() {
        clients.removeIf(ConnectedClient::isExpired);
    }

    public void forwardToAll(Message m) {
        clients.stream()
                .forEach(c -> NetworkHandler.sendMessage(m, c.getSocketAddress()));
    }
}
