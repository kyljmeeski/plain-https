package com.kyljmeeski.plainhttps.response;

import com.kyljmeeski.plainhttps.Body;
import com.kyljmeeski.plainhttps.Headers;
import com.kyljmeeski.plainhttps.Response;
import com.kyljmeeski.plainhttps.Status;
import com.kyljmeeski.plainhttps.body.PlainBody;

import java.util.Arrays;

public class PlainResponse implements Response {

    private final byte[] response;

    public PlainResponse(byte[] response) {
        this.response = response;
    }

    @Override
    public Status status() {
        for (int i = 0; i < response.length - 1; i++) {
            if (response[i] == 13 && response[i + 1] == 10) {
                byte[] bytes = Arrays.copyOfRange(response, 0, i);
                return new Status(bytes);
            }
        }
        return null;
    }

    @Override
    public Headers headers() {
        Headers headers = new Headers();
        int start = -1;
        for (int i = 0; i < response.length - 3; i++) {
            if (response[i] == 13 && response[i + 1] == 10) {
                if (start != -1) {
                    byte[] bytes = Arrays.copyOfRange(response, start + 2, i);
                    headers.add(bytes);
                    start = i;
                    if (response[i + 2] == 13 && response[i + 3] == 10) {
                        break;
                    }
                } else {
                    start = i;
                }
            }
        }
        return headers;
    }

    @Override
    public Body body() {
        String contentType = headers().get("Content-Type").orElse("text/plain");
        for (int i = 0; i < response.length - 3; i++) {
            if (response[i] == 13 && response[i + 1] == 10 && response[i + 2] == 13 && response[i + 3] == 10) {
                byte[] bytes = Arrays.copyOfRange(response, i + 4, response.length);
                return new PlainBody(bytes, contentType);
            }
        }
        return null;
    }
}
