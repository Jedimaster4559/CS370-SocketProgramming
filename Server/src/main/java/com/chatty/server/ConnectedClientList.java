package com.chatty.server;

import com.chatty.messages.Message;
import com.chatty.net.NetworkHandler;

import java.util.ArrayList;

public class ConnectedClientList {
    private ArrayList<ConnectedClient> clients = new ArrayList<>();

    public void heartbeatClient(String ip, int port) {
        for(ConnectedClient c : clients) {
            if(c.getIpAddress().equals(ip) && c.getPort() == port) {
                c.heartbeat();
                return;
            }
        }
        clients.add(new ConnectedClient(ip, port));
    }

    public void updateClients() {
        ArrayList<ConnectedClient> toRemove = new ArrayList<>();

        for(ConnectedClient c : clients) {
            if(c.isExpired()){
                toRemove.add(c);
            }
        }

        clients.removeAll(toRemove);
        toRemove.clear();
    }

    public void forwardToAll(Message m) {
        for(ConnectedClient c : clients) {
            NetworkHandler.sendMessage(m, c.getIpAddress(), c.getPort());
        }
    }
}
