package com.chatty.messages;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public abstract class Message {
    MessageType type;
    InetAddress source;

    public MessageType getType() {
        return type;
    }

    public InetAddress getSource() { return source; }

    public void setSource(InetAddress address) {
        this.source = address;
    }
}
