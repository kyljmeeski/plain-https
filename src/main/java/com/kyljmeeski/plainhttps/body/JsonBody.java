package com.kyljmeeski.plainhttps.body;

import com.google.gson.Gson;
import com.kyljmeeski.plainhttps.Body;

/**
 * Implementation of {@link Body} representing JSON content in an HTTP response.
 *
 * @param <T> the type of the JSON object
 */
public class JsonBody<T> implements Body {

    private final T json;

    /**
     * Constructs a new JsonBody with the specified JSON object.
     * <p>
     * The JSON object can be of any class and will be serialized into JSON format.
     *
     * @param json the object to include in the body, serialized into JSON
     */
    public JsonBody(T json) {
        this.json = json;
    }

    /**
     * Returns the content of the response body as bytes.
     *
     * @return the content of the response body as a byte array
     */
    @Override
    public byte[] content() {
        return new Gson().toJson(json).getBytes();
    }

    /**
     * Returns the MIME type of the content in the response body.
     *
     * @return the MIME type of the content in the response body as a String
     */
    @Override
    public String type() {
        return "application/json";
    }

}
