package com.chatty.net;

import com.chatty.messages.Message;
import com.chatty.messages.MessageDeserializer;
import com.chatty.util.Debug;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class MessageQueue {
//    private ArrayList<Message> messages = new ArrayList<>();
    private Queue<Message> messages = new ArrayDeque<>();

    /**
     * Adds new incoming json to the message queue
     * @param json
     */
    public synchronized void addJson(String json, InetAddress source) {
        Debug.log(json);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Message.class, new MessageDeserializer())
                .registerTypeAdapter(InetSocketAddress.class, new InetSocketAddressDeserializer())
                .create();
        Message message = gson.fromJson(json, Message.class);
        message.setSource(source);
        messages.add(message);
    }

    public synchronized Message dequeue() {
        return messages.poll();
    }
}
