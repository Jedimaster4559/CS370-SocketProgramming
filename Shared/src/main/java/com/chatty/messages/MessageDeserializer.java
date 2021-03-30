package com.chatty.messages;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MessageDeserializer implements JsonDeserializer<Message> {
    @Override
    public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // Parse Out Type
        JsonObject jsonObj = json.getAsJsonObject();
        MessageType type = MessageType.valueOf(jsonObj.get("type").getAsString());

        // Determine Type
        Class clazz = null;
        switch(type) {
            case CHAT_MESSAGE:
                clazz = ChatMessage.class;
                break;
            case HEARTBEAT:
                clazz = Heartbeat.class;
                break;
        }

        // Deserialize
        try {
            return context.deserialize(json, clazz);
        } catch (NullPointerException e) {
            throw new JsonParseException(e);
        }
    }
}
