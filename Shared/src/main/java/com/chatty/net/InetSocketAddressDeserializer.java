package com.chatty.net;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.net.InetSocketAddress;

public class InetSocketAddressDeserializer implements JsonDeserializer<InetSocketAddress> {
    @Override
    public InetSocketAddress deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(jsonObject.get("address").getAsString(), jsonObject.get("port").getAsInt());
        return inetSocketAddress;
    }
}
