package com.chatty.messages;

public class Heartbeat extends Message {
    public Heartbeat() {
        this.type = MessageType.HEARTBEAT;
    }
}
