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
package com.kyljmeeski.plainhttps.response;

import com.kyljmeeski.plainhttps.Body;
import com.kyljmeeski.plainhttps.Headers;
import com.kyljmeeski.plainhttps.Response;
import com.kyljmeeski.plainhttps.Status;
import com.kyljmeeski.plainhttps.body.PlainBody;

import java.util.Arrays;

/**
 * Implementation of the {@link Response} interface for handling plain HTTP responses.
 */
public class PlainResponse implements Response {

    private final byte[] response;

    /**
     * Constructs a new PlainResponse object with the given byte array representation of the HTTP response.
     *
     * @param response the byte array representing the HTTP response
     */
    public PlainResponse(byte[] response) {
        this.response = response;
    }

    /**
     * Retrieves the status of the HTTP response.
     *
     * @return the status of the response as a {@link Status} object
     */
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

    /**
     * Retrieves the headers of the HTTP response.
     *
     * @return the headers of the response as a {@link Headers} object
     */
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

    /**
     * Retrieves the body of the HTTP response.
     *
     * @return the body of the response as an implementation of {@link Body}
     */
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
