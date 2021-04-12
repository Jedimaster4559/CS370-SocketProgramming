package com.chatty.messages;

public class ClientConnectMessage extends Message {
    private int receivePort;

    public ClientConnectMessage(int receivePort) {
        this.type = MessageType.CLIENT_CONNECT;
        this.receivePort = receivePort;
    }

    public int getReceivePort() { return receivePort; }
}
