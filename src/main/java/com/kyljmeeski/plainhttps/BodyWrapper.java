package com.kyljmeeski.plainhttps;

import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

class GenericDeserializer<T> implements JsonDeserializer<T> {

    private final Class<T> clazz;

    public GenericDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        for (Field field : clazz.getDeclaredFields()) {
            if (!jsonObject.has(field.getName())) {
                throw new JsonParseException("Missing field: " + field.getName());
            }
        }

        return new Gson().fromJson(jsonObject, clazz);
    }

}

public class BodyWrapper {

    private final byte[] content;

    public BodyWrapper(Body body) {
        content = body.content();
    }

    public <T> T content(Class<T> clazz) throws MissingFieldException {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(clazz, new GenericDeserializer<>(clazz));
            Gson gson = gsonBuilder.create();
            return gson.fromJson(new String(content), clazz);
        } catch (JsonParseException exception) {
            throw new MissingFieldException(exception.getMessage());
        }
    }

}
