package com.chatty.messages;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class MessageDeserializer implements JsonDeserializer<Message> {

    private final Map<MessageType, Class> classMap = new EnumMap<>(MessageType.class);

    public MessageDeserializer() {
        classMap.put(MessageType.CHAT, ChatMessage.class);
        classMap.put(MessageType.HEARTBEAT, HeartbeatMessage.class);
        classMap.put(MessageType.CLIENT_CONNECT, ClientConnectMessage.class);
        classMap.put(MessageType.CLIENT_DISCONNECT, ClientDisconnectMessage.class);
        classMap.put(MessageType.SERVER_COMMAND, ServerCommandMessage.class);
    }

    @Override
    public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // Parse Out Type
        JsonObject jsonObj = json.getAsJsonObject();
        MessageType type = MessageType.valueOf(jsonObj.get("type").getAsString());

        // Determine Type
        Class clazz = classMap.get(type);

        // Deserialize
        try {
            Message deserialize = context.deserialize(json, clazz);
            return deserialize;
        } catch (NullPointerException e) {
            throw new JsonParseException(e);
        }
    }
}
