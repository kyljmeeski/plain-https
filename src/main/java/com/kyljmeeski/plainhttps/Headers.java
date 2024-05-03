package com.kyljmeeski.plainhttps;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Headers {

    private final Map<String, String> headers;

    public Headers() {
        this(new HashMap<>());
    }

    public Headers(Map<String, String> headers) {
        this.headers = headers;
    }

    public void add(String key, String value) {
        headers.put(key, value);
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(headers.get(key));
    }

}
