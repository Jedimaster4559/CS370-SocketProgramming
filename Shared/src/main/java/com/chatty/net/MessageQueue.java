package com.chatty.net;

import com.chatty.messages.Message;
import com.chatty.messages.MessageDeserializer;
import com.chatty.util.Debug;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

class MessageQueue {
    private ArrayList<Message> messages = new ArrayList<>();

    /**
     * Adds new incoming json to the message queue
     * @param json
     */
    public synchronized void addJson(String json) {
        Debug.log(json);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Message.class, new MessageDeserializer())
                .create();
        Message message = gson.fromJson(json, Message.class);
        messages.add(message);
    }

    public synchronized Message dequeue() {
        if(messages.size() > 0) {
            Message message = messages.get(0);
            messages.remove(0);
            return message;
        } else {
            return null;
        }
    }
}
