package com.chatty.messages;

import com.chatty.util.Debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Heartbeat extends Message {
    /** The port that this system is listening on. */
    public int port;
    public String ipAddress;

    public Heartbeat(int port) {
        this.type = MessageType.HEARTBEAT;
        this.port = port;

        // Set Sender IP Address
        try {
            URL whatIsMyIp = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatIsMyIp.openStream()));
            ipAddress = in.readLine();
        } catch (IOException e) {
            Debug.error("An error occurred when trying to determine my ip address");
        }
    }
}
