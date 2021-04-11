package com.chatty.data;

public class Chat {
    User sender;
    String body;

    public Chat(User sender, String body) {
        this.sender = sender;
        this.body = body;
    }

    /**
     * Gets the actual contents of the chat message.
     * @return The message itself.
     */
    public String getBody(){
        return body;
    }
    /**
     *The getUser method allows the sender variable to be accessed by other classes.
     */
    public User getUser() {
        return sender;
    }
}
