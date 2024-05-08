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
package com.kyljmeeski.plainhttps.request;

/**
 * Represents a URL and provides methods to extract host and URI parts.
 */
public class Url {

    private final String url;

    /**
     * Constructs a new Url object with the specified URL string.
     *
     * @param url the URL string
     */
    public Url(String url) {
        this.url = url;
    }

    /**
     * Extracts and returns the host part of the URL.
     *
     * @return the host part of the URL
     */
    public String host() {
        if (url.startsWith("https://")) {
            return url.substring(8).split("/", 2)[0];
        } else if (url.startsWith("http://")) {
            return url.substring(7).split("/", 2)[0];
        }
        return url.split("/", 2)[0];
    }

    /**
     * Extracts and returns the URI part of the URL.
     *
     * @return the URI part of the URL
     */
    public String uri() {
        if (url.startsWith("https://")) {
            return "/" + url.substring(8).split("/", 2)[1];
        } else if (url.startsWith("http://")) {
            return "/" + url.substring(7).split("/", 2)[1];
        }
        String[] parts = url.split("/", 2);
        if (parts.length > 1) {
            return "/" + url.split("/", 2)[1];
        }
        return "/";
    }

}
