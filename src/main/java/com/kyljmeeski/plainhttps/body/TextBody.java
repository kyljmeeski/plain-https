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
package com.kyljmeeski.plainhttps.body;

import com.kyljmeeski.plainhttps.Body;

/**
 * Implementation of {@link Body} representing text content in an HTTP response.
 */
public class TextBody implements Body {

    private final String text;

    /**
     * Constructs a new TextBody with the specified text content.
     *
     * @param text the text content of the body
     */
    public TextBody(String text) {
        this.text = text;
    }

    /**
     * Returns the content of the response body as bytes.
     *
     * @return the content of the response body as a byte array
     */
    @Override
    public byte[] content() {
        return text.getBytes();
    }

    /**
     * Returns the MIME type of the content in the response body.
     *
     * @return the MIME type of the content in the response body as a String
     */
    @Override
    public String type() {
        return "text/plain";
    }

}
