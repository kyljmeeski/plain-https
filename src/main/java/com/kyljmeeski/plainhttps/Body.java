package com.kyljmeeski.plainhttps;

/**
 * Interface representing the body of an HTTP response.
 */
public interface Body {

    /**
     * Returns the content of the response body as bytes.
     *
     * @return the content of the response body as a byte array
     */
    byte[] content();

    /**
     * Returns the MIME type of the content in the response body.
     *
     * @return the MIME type of the content in the response body as a String
     */
    String type();

}
