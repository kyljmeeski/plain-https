package com.kyljmeeski.plainhttps.body;

import com.kyljmeeski.plainhttps.Body;

/**
 * Implementation of {@link Body} representing generic content in an HTTP response.
 */
public class PlainBody implements Body {

    private final byte[] content;
    private final String type;

    /**
     * Constructs a new PlainBody with the specified content and MIME type.
     *
     * @param content the content of the body as a byte array
     * @param type    the MIME type of the content
     */
    public PlainBody(byte[] content, String type) {
        this.content = content;
        this.type = type;
    }

    /**
     * Returns the content of the response body as bytes.
     *
     * @return the content of the response body as a byte array
     */
    @Override
    public byte[] content() {
        return content;
    }

    /**
     * Returns the MIME type of the content in the response body.
     *
     * @return the MIME type of the content in the response body as a String
     */
    @Override
    public String type() {
        return type;
    }

}
