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
