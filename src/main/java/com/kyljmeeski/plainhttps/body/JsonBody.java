package com.kyljmeeski.plainhttps.body;

import com.google.gson.Gson;
import com.kyljmeeski.plainhttps.Body;

public class JsonBody<T> implements Body {

    private final T json;

    public JsonBody(T json) {
        this.json = json;
    }

    @Override
    public byte[] content() {
        return new Gson().toJson(json).getBytes();
    }

    @Override
    public String type() {
        return "application/json";
    }

}
