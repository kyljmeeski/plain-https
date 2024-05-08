package com.kyljmeeski.plainhttps.request;

/**
 * Interface representing a request line in an HTTP request.
 */
public interface RequestLine {

    /**
     * Converts the request line to a byte array.
     *
     * @return the request line as a byte array
     */
    byte[] bytes();

}
