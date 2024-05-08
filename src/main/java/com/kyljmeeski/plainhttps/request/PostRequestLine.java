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

public class PostRequestLine implements RequestLine {

    private final String uri;
    public final String version;

    /**
     * Constructs a new PostRequestLine with the specified URI, using HTTP/1.1 version by default.
     *
     * @param uri the URI of the resource to post to
     */
    public PostRequestLine(String uri) {
        this(uri, "HTTP/1.1");
    }

    /**
     * Constructs a new PostRequestLine with the specified URI and HTTP version.
     *
     * @param uri     the URI of the resource to post to
     * @param version the HTTP version to use (e.g., "HTTP/1.1")
     */
    public PostRequestLine(String uri, String version) {
        this.uri = uri;
        this.version = version;
    }

    /**
     * Converts the request line to a byte array.
     *
     * @return the request line as a byte array
     */
    @Override
    public byte[] bytes() {
        return String.format("POST %s %s\r\n", uri, version).getBytes();
    }

}
