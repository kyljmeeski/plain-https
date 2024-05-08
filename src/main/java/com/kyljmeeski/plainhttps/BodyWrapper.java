/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2024 Amir Syrgabaev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
