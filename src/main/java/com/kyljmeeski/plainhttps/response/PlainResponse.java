package com.kyljmeeski.plainhttps.response;

import com.kyljmeeski.plainhttps.Body;
import com.kyljmeeski.plainhttps.Headers;
import com.kyljmeeski.plainhttps.Response;
import com.kyljmeeski.plainhttps.Status;

public class PlainResponse implements Response {

    private final byte[] response;

    public PlainResponse(byte[] response) {
        this.response = response;
    }

    @Override
    public Status status() {
        return null;
    }

    @Override
    public Headers headers() {
        return null;
    }

    @Override
    public Body body() {
        return null;
    }
}
