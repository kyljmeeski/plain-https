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

/**
 * Wrapper class for handling HTTP response bodies.
 */
public class BodyWrapper {

    private final byte[] content;

    /**
     * Constructs a new BodyWrapper with the specified body content.
     *
     * @param body the HTTP response body to wrap
     */
    public BodyWrapper(Body body) {
        content = body.content();
    }

    /**
     * Deserializes the wrapped HTTP response body into an object of the specified class.
     *
     * @param <T>   the type of the object to deserialize to
     * @param clazz the class of the object to deserialize to
     * @return an object of the specified class representing the deserialized body content
     * @throws MissingFieldException if a required field is missing during deserialization
     */
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
