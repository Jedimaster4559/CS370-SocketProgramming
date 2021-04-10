package com.chatty.net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.net.InetSocketAddress;

public class InetSocketAddressSerializer implements JsonSerializer<InetSocketAddress> {
    @Override
    public JsonElement serialize(InetSocketAddress socketAddress, Type type, JsonSerializationContext jsonSerializationContext) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("address", gson.toJsonTree(socketAddress.getAddress()));
        jsonObject.add("port", new JsonPrimitive(socketAddress.getPort()));
        return jsonObject;
    }
}
