package com.chatty.messages;

import com.chatty.util.Debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;

public class HeartbeatMessage extends Message {
    public InetSocketAddress socketAddress;

    public HeartbeatMessage(InetSocketAddress socketAddress) {
        this.type = MessageType.HEARTBEAT;
        this.socketAddress = socketAddress;
    }

    public InetSocketAddress getSocketAddress() {
        return socketAddress;
    }
}
