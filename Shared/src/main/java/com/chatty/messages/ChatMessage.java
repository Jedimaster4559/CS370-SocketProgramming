package com.chatty.messages;

import com.chatty.data.Chat;

public class ChatMessage extends Message{
    private Chat data;

    public ChatMessage(Chat body) {
        this.type = MessageType.CHAT_MESSAGE;
        this.data = body;
    }

    /**
     * Gets the actual main body of the message sent.
     * @return The chat message sent.
     */
    public Chat getChat(){
        return data;
    }
}
